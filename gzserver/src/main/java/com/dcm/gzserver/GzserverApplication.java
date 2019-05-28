package com.dcm.gzserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@EnableDubbo
@SpringBootApplication
public class GzserverApplication {

    public static void main(String[] args) {

        SpringApplication.run(GzserverApplication.class, args);

    }

}
