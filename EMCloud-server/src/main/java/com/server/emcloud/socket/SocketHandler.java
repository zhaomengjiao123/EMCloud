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
        // ??????????????????????????????otherService?????????
        socketHandler.taskRecordService = this.taskRecordService;
        socketHandler.taskService = this.taskService;
    }



    /**
    * @Description: ????????????Socket?????????Socket??????
    * @Param: [socket]
    * @return: com.server.emcloud.socket.ClientSocket
    * @Author: zmj
    * @Date: 2022/7/9
    */
    public static ClientSocket register(Socket socket){
        ClientSocket clientSocket = new ClientSocket();
        //?????????????????????
        InetAddress client_InetAddress = socket.getInetAddress();
        System.out.println("?????????????????????"+client_InetAddress.toString());
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
    * @Description: ??????????????????????????????
    * @Param: [clientSocket, message]
    * @return: void
    * @Author: zmj
    * @Date: 2022/7/9
    */

    public static void sendMessage(ClientSocket clientSocket, String message){
        try {
            log.info("????????????????????????  : >>>>>" + message);
            clientSocket.getOutputStream().write(message.getBytes("utf-8"));
            //clientSocket.getOutputStream().writeUTF(message);
        } catch (IOException e) {
            log.error("?????????????????????{}", e);
            close(clientSocket);
        }
    }

    /**
    * @Description: ????????????????????????????????????
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


                    if(type==1){   //??????
                        AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
                        //agvStateInfo.setSendTime(LocalDateTime.now().toString());
                        int agvid = new Integer(agvStateInfo.getAgvid());
                        System.out.println("??????????????????ID???"+agvid);
                        //????????????????????????????????????
                        int res = socketHandler.agvInfoService.addAgvStateInfo(agvStateInfo);
                        if(res>0){
                            log.info("????????????????????????");
                        }

                        //?????????????????????
                        ExceptionCode exceptionCode = new ExceptionCode();
                        String exception = "";
                        int erro_code = (int) agvStateInfo.getError_code();
                        if(erro_code!=0){
                            log.info("????????????",erro_code);
                            exception=exceptionCode.getException(erro_code,"error");
                            EquipmentErro equipmentErro = new EquipmentErro();
                            equipmentErro.setEquipment_id(agvid);
                            equipmentErro.setErro_content(exception);
                            equipmentErro.setErro_time(agvStateInfo.getSendtime());
                            //??????????????????
                            if(socketHandler.equipmentErroService.addErroInfo(equipmentErro)>0){
                                log.info("????????????????????????",exception);
                                //?????????????????????+1
                                socketHandler.equipmentService.updateErroCountByEid(agvid);
                                log.info("??????????????????????????????");
                            }

                        }
                        int warning_code = (int) agvStateInfo.getWarning_code();
                        if(warning_code!=0){
                            log.info("????????????",warning_code);
                            exception=exceptionCode.getException(warning_code,"warning");
                            EquipmentWarning equipmentWarning = new EquipmentWarning();
                            equipmentWarning.setEquipment_id(agvid);
                            equipmentWarning.setWarning_content(exception);
                            equipmentWarning.setWarning_time(agvStateInfo.getSendtime());

                            //??????????????????
                            if(socketHandler.equipmentWarningService.addWarningInfo(equipmentWarning)>0){
                                log.info("????????????????????????",exception);
                                //?????????????????????+1
                                socketHandler.equipmentService.updateWarningCountByEid(agvid);
                                log.info("??????????????????????????????");
                            }

                        }


                        EquipmentState equipmentState = new EquipmentState();
                        equipmentState.setEquipment_id(agvid);
                        equipmentState.setState_update_time(agvStateInfo.getSendtime());
                        //??????????????????
                        int exist_id_count = socketHandler.equipmentStateService.getEquipmentStateIDCount(agvid);
                        System.out.println("ee:"+exist_id_count);
                        if(exist_id_count>0){
                            //????????????????????????
                            socketHandler.equipmentStateService.updateEquipmentState(equipmentState);
                            log.info("????????????");
                        }else {
                            //??????
                            log.info("????????????");
                            socketHandler.equipmentStateService.addEquipmentState(equipmentState);
                        }

                    }else if(type==2){  //??????
                        TaskRecord taskRecord = (TaskRecord) JSONObject.toBean(jsonobject,TaskRecord.class);
                        System.out.println("??????????????????ID???"+taskRecord.getTaskID());
                        //????????????????????????????????????
                        int res = socketHandler.taskRecordService.addTaskRecord(taskRecord);
                        if(res>0){
                            log.info("TaskRecord???????????????");
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
                            log.info("Task???????????????");
                        }
                    }


//                    AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
//                    System.out.println("??????????????????ID???"+agvStateInfo.getAgvid());
//                    //????????????????????????????????????
//                        int res = socketHandler.agvInfoService.addAgvStateInfo(agvStateInfo);
//                        if(res>0){
//                            log.info("????????????????????????");
//                        }

                        //????????????
                        if (clientSocket.getInputStream().available() == 0) {
                            //??????,?????????????????????????????????????????????
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
    * @Description: ??????Socket????????????
    * @Param: [clientSocket]
    * @return: void
    * @Author: zmj
    * @Date: 2022/7/9
    */
    public static void close(ClientSocket clientSocket){
        log.info("??????????????????");
        if (clientSocket != null){
            System.out.println("????????????socket??????????????????Key???{}"+ clientSocket.getKey());
            remove(clientSocket.getKey());
            Socket socket = clientSocket.getSocket();
            try {
                socket.shutdownInput();
                socket.shutdownOutput();
            } catch (IOException e) {
                System.out.println("??????????????????????????????{}"+e);
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("??????socket??????{}"+ e);
                }
            }
        }
    }


    /**
    * @Description: ????????????????????????
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


