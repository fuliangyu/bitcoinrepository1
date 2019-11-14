package com.fly.bitcoin1112;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.fly.bitcoin1112.dao")
@EnableFeignClients
@EnableAsync
public class Bitcoinship1112Application {

    public static void main(String[] args) {
        SpringApplication.run(Bitcoinship1112Application.class, args);
    }

}
