package com.server.emcloud.socket;

import com.alibaba.druid.util.StringUtils;
import com.server.emcloud.domain.AGVProtocolHeader;
import com.server.emcloud.domain.AgvStateInfo;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.set.SynchronizedSet;

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
public class SocketHandler {

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
    public static void onMessage(ClientSocket clientSocket){
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
                    //JSONObject agvJson = JSONObject.parseObject(info);
                    JSONObject jsonobject = JSONObject.fromObject(info);
                    AgvStateInfo agvStateInfo = (AgvStateInfo) JSONObject.toBean(jsonobject,AgvStateInfo.class);
                    System.out.println("转换后的对象ID："+agvStateInfo.getAgvid());

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


