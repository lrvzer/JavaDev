package com.rabbitmq.test;

import com.rabbitmq.comp.SenderComponent;
import com.rabbitmq.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SenderTest {

    @Autowired
    SenderComponent senderComponent;

    @Test
    void sendStringToMQ() {
        String str = "hello, SpringBoot";
        Map<String, Object> props = new HashMap<>();
        props.put("Color", "Pink");
        props.put("Size", "16px");
        senderComponent.send(str, props);
    }

    @Test
    void sendUserToMQ() {
        User tom = new User("Tom", 10, 1000);
        senderComponent.send(tom);
    }

}
