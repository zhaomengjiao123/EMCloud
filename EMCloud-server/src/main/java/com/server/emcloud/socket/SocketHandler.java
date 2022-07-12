package com.server.emcloud.socket;

import com.alibaba.druid.util.StringUtils;
import com.server.emcloud.dao.AgvStateInfoMapper;
import com.server.emcloud.domain.AGVProtocolHeader;
import com.server.emcloud.domain.AgvStateInfo;
import com.server.emcloud.domain.Task;
import com.server.emcloud.domain.TaskRecord;
import com.server.emcloud.service.AgvInfoService;
import com.server.emcloud.service.TaskRecordService;
import com.server.emcloud.service.TaskService;
import com.server.emcloud.service.impl.AgvInfoSErviceImpl;
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
import java.time.LocalDateTime;

import static com.server.emcloud.socket.SocketPool.add;
import static com.server.emcloud.socket.SocketPool.remove;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/21:32
 * @Description:
 */
@Slf4j
@Component
public class SocketHandler {


    @Autowired
    private AgvInfoService agvInfoService;
    private TaskRecordService taskRecordService;
    private TaskService taskService;
    static Logger log = LoggerFactory.getLogger(SocketHandler.class);

    public static SocketHandler socketHandler;

    @PostConstruct
    public void init() {
        socketHandler = this;
        socketHandler.agvInfoService = this.agvInfoService ;
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
                    //System.out.println(info);
                    //JSONObject agvJson = JSONObject.parseObject(info);
                    JSONObject jsonobject = JSONObject.fromObject(info);


                    if(type==1){   //状态

                        AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
                        System.out.println("转换后的对象ID："+agvStateInfo.getAgvid());
                        //将消息体对象添加到数据库
                        int res = socketHandler.agvInfoService.addAgvStateInfo(agvStateInfo);
                        if(res>0){
                            log.info("添加消息体成功！");
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
                        task.setTask_start_time(taskRecord.getBeginTime());
                        task.setTask_end_time(taskRecord.getEndTime());
                        int res1 = socketHandler.taskService.addTask(task);
                        if(res1>0){
                            log.info("Task添加成功！");
                        }
                    }


                    AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
                    System.out.println("转换后的对象ID："+agvStateInfo.getAgvid());
                    //将消息体对象添加到数据库
                        int res = socketHandler.agvInfoService.addAgvStateInfo(agvStateInfo);
                        if(res>0){
                            log.info("添加消息体成功！");
                        }

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


