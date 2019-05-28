package com.dcm.shserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ShserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShserverApplication.class, args);
    }

}
