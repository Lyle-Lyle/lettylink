package com.letty.lettylink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.letty.lettylink.admin.dao.mapper")
public class LettyLinkAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(LettyLinkAdminApplication.class, args);
    }
}
