package com.server.emcloud.socket;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.server.emcloud.socket.SocketHandler.register;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zmj
 * @Date: 2022/07/08/20:21
 * @Description:
 */
@Service
@Data
@Component
@NoArgsConstructor
public class SocketServer {
    private Integer port = 9090;
    private boolean started;
    private ServerSocket serverSocket;
    // 防止重复创建socket线程链接对象浪费资源
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private Logger log = LoggerFactory.getLogger(SocketServer.class);

    public void start(){
        start(null);
    }

    public void start(Integer port){
        log.info("port: {}, {}", this.port, port);
        try {
            serverSocket =  new ServerSocket(port == null ? this.port : port);
            started = true;
            log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
        } catch (IOException e) {
            log.error("端口冲突,异常信息：{}", e);
            System.exit(0);
        }


        while  (true){
            try {
                // 开启socket监听
                Socket socket = serverSocket.accept();
                ClientSocket register = register(socket);
                System.out.println("socket创建成功");
                // 在此判断是否重复创建socket对象线程
                if (register != null){
                    executorService.submit(register);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
