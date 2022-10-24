package com.blog.sso;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.blog.sso.dao"})
public class BlogSSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSSOApplication.class, args);
    }

}
