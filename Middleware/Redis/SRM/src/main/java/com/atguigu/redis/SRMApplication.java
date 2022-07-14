package com.atguigu.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.atguigu.redis.mapper")
public class SRMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SRMApplication.class, args);
    }
}
