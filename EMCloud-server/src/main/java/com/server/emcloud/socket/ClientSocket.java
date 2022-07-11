package com.server.emcloud.socket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

import static com.server.emcloud.socket.SocketHandler.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/21:31
 * @Description:
 */
@Slf4j
@Data
public class ClientSocket implements Runnable {

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String key;
    private String message;
    private SocketHandler socketHandler=new SocketHandler();

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("1"+this);

                socketHandler.onMessage(this);
                System.out.println(LocalDateTime.now()+" 接收到数据: <<<<<<" + this.message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isSocketClosed(this)){
                log.info("客户端已关闭,其Key值为：{}",this.getKey());
                //关闭对应的服务端资源
                close(this);
                break;
            }
        }
    }
}


