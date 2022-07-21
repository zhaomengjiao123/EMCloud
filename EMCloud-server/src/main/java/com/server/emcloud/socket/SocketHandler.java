package com.server.emcloud.socket;

import com.alibaba.druid.util.StringUtils;
import com.server.emcloud.dao.AgvStateInfoMapper;
import com.server.emcloud.domain.*;
import com.server.emcloud.service.*;
import com.server.emcloud.service.impl.AgvInfoSErviceImpl;
import com.server.emcloud.utils.ExceptionCode;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.set.SynchronizedSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static com.server.emcloud.socket.SocketPool.add;
import static com.server.emcloud.socket.SocketPool.remove;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/21:32
 * @Description:
 */

@Component
public class SocketHandler {


    @Autowired
    private AgvInfoService agvInfoService;
    @Autowired
    private EquipmentStateService equipmentStateService;
    @Autowired
    private EquipmentErroService equipmentErroService;
    @Autowired
    private EquipmentWarningService equipmentWarningService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private TaskRecordService taskRecordService;
    @Autowired
    private TaskService taskService;
    static Logger log = LoggerFactory.getLogger(SocketHandler.class);

    public static SocketHandler socketHandler;

    @PostConstruct
    public void init() {
        socketHandler = this;
        socketHandler.equipmentStateService = this.equipmentStateService;
        socketHandler.agvInfoService = this.agvInfoService ;
        socketHandler.equipmentErroService = this.equipmentErroService;
        socketHandler.equipmentWarningService = this.equipmentWarningService;
        socketHandler.equipmentService = this.equipmentService;
        // 初使化时将已静态化的otherService实例化
        socketHandler.taskRecordService = this.taskRecordService;
        socketHandler.taskService = this.taskService;
    }



    /**
    * @Description: 将连接的Socket注册到Socket池中
    * @Param: [socket]
    * @return: com.server.emcloud.socket.ClientSocket
    * @Author: zmj
    * @Date: 2022/7/9
    */
    public static ClientSocket register(Socket socket){
        ClientSocket clientSocket = new ClientSocket();
        //得到客户端地址
        InetAddress client_InetAddress = socket.getInetAddress();
        System.out.println("客户端地址为："+client_InetAddress.toString());
        clientSocket.setSocket(socket);
        try {
            clientSocket.setInputStream(new DataInputStream(socket.getInputStream()));
            clientSocket.setOutputStream(new DataOutputStream(socket.getOutputStream()));
            clientSocket.setKey(client_InetAddress.toString());
            add(clientSocket);
            return clientSocket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: 向指定客户端发送信息
    * @Param: [clientSocket, message]
    * @return: void
    * @Author: zmj
    * @Date: 2022/7/9
    */

    public static void sendMessage(ClientSocket clientSocket, String message){
        try {
            log.info("发送消息到客户端  : >>>>>" + message);
            clientSocket.getOutputStream().write(message.getBytes("utf-8"));
            //clientSocket.getOutputStream().writeUTF(message);
        } catch (IOException e) {
            log.error("发送信息异常：{}", e);
            close(clientSocket);
        }
    }

    /**
    * @Description: 获取指定客户端的上传信息
    * @Param: [clientSocket]
    * @return: void
    * @Author: zmj
    * @Date: 2022/7/9
    */
    public void onMessage(ClientSocket clientSocket){
        DataInputStream dataInputStream = clientSocket.getInputStream();
        try {
                String info = "";
                System.out.println("2");
                while (true) {
                    if (dataInputStream.available()>0) {
                    byte sync = dataInputStream.readByte();
                    byte version = dataInputStream.readByte();
                    short number = dataInputStream.readShort();
                    int total_length = dataInputStream.readInt();
                    short type = dataInputStream.readShort();
                    int reserved = dataInputStream.read(new byte[6]);
                    System.out.println(sync+" "+version+" "+number+" "+total_length+" "+type+" "+reserved);
                    byte[] m=new byte[total_length];
                    dataInputStream.read(m);

                    info += new String(m,"utf-8");
                    JSONObject jsonobject = JSONObject.fromObject(info);


                    if(type==1){   //状态
                        AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
                        //agvStateInfo.setSendTime(LocalDateTime.now().toString());
                        int agvid = new Integer(agvStateInfo.getAgvid());
                        System.out.println("转换后的对象ID："+agvid);
                        //将消息体对象添加到数据库
                        int res = socketHandler.agvInfoService.addAgvStateInfo(agvStateInfo);
                        if(res>0){
                            log.info("添加消息体成功！");
                        }

                        //处理报警与预警
                        ExceptionCode exceptionCode = new ExceptionCode();
                        String exception = "";
                        int erro_code = (int) agvStateInfo.getError_code();
                        if(erro_code!=0){
                            log.info("报警码：",erro_code);
                            exception=exceptionCode.getException(erro_code,"error");
                            EquipmentErro equipmentErro = new EquipmentErro();
                            equipmentErro.setEquipment_id(agvid);
                            equipmentErro.setErro_content(exception);
                            equipmentErro.setErro_time(agvStateInfo.getSendtime());
                            //添加报警信息
                            if(socketHandler.equipmentErroService.addErroInfo(equipmentErro)>0){
                                log.info("添加报警信息成功",exception);
                                //设备的报警数量+1
                                socketHandler.equipmentService.updateErroCountByEid(agvid);
                                log.info("设备报警数量更新成功");
                            }

                        }
                        int warning_code = (int) agvStateInfo.getWarning_code();
                        if(warning_code!=0){
                            log.info("预警码：",warning_code);
                            exception=exceptionCode.getException(warning_code,"warning");
                            EquipmentWarning equipmentWarning = new EquipmentWarning();
                            equipmentWarning.setEquipment_id(agvid);
                            equipmentWarning.setWarning_content(exception);
                            equipmentWarning.setWarning_time(agvStateInfo.getSendtime());

                            //添加预警信息
                            if(socketHandler.equipmentWarningService.addWarningInfo(equipmentWarning)>0){
                                log.info("添加预警信息成功",exception);
                                //设备的预警数量+1
                                socketHandler.equipmentService.updateWarningCountByEid(agvid);
                                log.info("设备预警数量更新成功");
                            }

                        }


                        EquipmentState equipmentState = new EquipmentState();
                        equipmentState.setEquipment_id(agvid);
                        equipmentState.setState_update_time(agvStateInfo.getSendtime());
                        //是否已经存在
                        int exist_id_count = socketHandler.equipmentStateService.getEquipmentStateIDCount(agvid);
                        System.out.println("ee:"+exist_id_count);
                        if(exist_id_count>0){
                            //已经存在直接更新
                            socketHandler.equipmentStateService.updateEquipmentState(equipmentState);
                            log.info("更新成功");
                        }else {
                            //添加
                            log.info("添加成功");
                            socketHandler.equipmentStateService.addEquipmentState(equipmentState);
                        }

                    }else if(type==2){  //任务
                        TaskRecord taskRecord = (TaskRecord) JSONObject.toBean(jsonobject,TaskRecord.class);
                        System.out.println("转换后的对象ID："+taskRecord.getTaskID());
                        //将消息体对象添加到数据库
                        int res = socketHandler.taskRecordService.addTaskRecord(taskRecord);
                        if(res>0){
                            log.info("TaskRecord添加成功！");
                        }

                        Task task=new Task();
                        task.setTask_id(Integer.parseInt(taskRecord.getTaskID()));
                        task.setEquipment_id(Integer.parseInt(taskRecord.getAGVID()));
                        String [] a = taskRecord.getBeginTime().split(" ");
                        String [] b = taskRecord.getEndTime().split(" ");
                        task.setTask_start_time(a[0]);
                        task.setTask_end_time(a[1]);

                        int res1 = socketHandler.taskService.addTask(task);
                        if(res1>0){
                            log.info("Task添加成功！");
                        }
                    }


//                    AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
//                    System.out.println("转换后的对象ID："+agvStateInfo.getAgvid());
//                    //将消息体对象添加到数据库
//                        int res = socketHandler.agvInfoService.addAgvStateInfo(agvStateInfo);
//                        if(res>0){
//                            log.info("添加消息体成功！");
//                        }

                        //已经读完
                        if (clientSocket.getInputStream().available() == 0) {
                            //重置,不然每次收到的数据都会累加起来
                            clientSocket.setMessage(info);
                            break;
                        }
                    //}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            close(clientSocket);
        }
    }


    /**
    * @Description: 指定Socket资源回收
    * @Param: [clientSocket]
    * @return: void
    * @Author: zmj
    * @Date: 2022/7/9
    */
    public static void close(ClientSocket clientSocket){
        log.info("进行资源回收");
        if (clientSocket != null){
            System.out.println("开始回收socket相关资源，其Key为{}"+ clientSocket.getKey());
            remove(clientSocket.getKey());
            Socket socket = clientSocket.getSocket();
            try {
                socket.shutdownInput();
                socket.shutdownOutput();
            } catch (IOException e) {
                System.out.println("关闭输入输出流异常，{}"+e);
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("关闭socket异常{}"+ e);
                }
            }
        }
    }


    /**
    * @Description: 判断数据连接状态
    * @Param: [clientSocket]
    * @return: boolean
    * @Author: zmj
    * @Date: 2022/7/9
    */
    public static boolean isSocketClosed(ClientSocket clientSocket){
        try {
            clientSocket.getSocket().sendUrgentData(1);
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}


