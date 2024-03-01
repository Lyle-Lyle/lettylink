package com.letty.lettylink.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.letty.lettylink.application.dao.mapper")
public class LettyLinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(LettyLinkApplication.class, args);
    }
}
