package com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducerRunner.class, args);
    }

}
