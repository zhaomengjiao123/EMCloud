package com.server.emcloud;

import com.server.emcloud.controller.TestController;
import com.server.emcloud.socket.SocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication
@MapperScan("com.server.emcloud.dao")
@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EmCloudApplication {

    public static void main(String[] args) {
//        SpringApplication.run(EmCloudApplication.class, args);
////        SpringApplication.getBean(SocketServer.class).start();

        ApplicationContext applicationContext = SpringApplication.run(EmCloudApplication.class, args);
        applicationContext.getBean(SocketServer.class).start();



    }

}