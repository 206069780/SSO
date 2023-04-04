package com.senrui.sso;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.senrui.sso.dao"})
public class SSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }

}
