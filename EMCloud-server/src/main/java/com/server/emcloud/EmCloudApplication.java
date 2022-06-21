package com.server.emcloud;

import com.server.emcloud.controller.TestController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@MapperScan("com.server.emcloud.dao")
@EnableScheduling
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EmCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmCloudApplication.class, args);

    }

}
