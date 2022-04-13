package com.rabbitmq.rabbittemplate;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Map;

@SpringBootTest
public class ReceiveMethodTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void testReceive() {
        while (true) {
            Message msg = rabbitTemplate.receive("Spring_Queue", 2000);
            if (msg == null) {
                break;
            }
            String str = new String(msg.getBody());
            System.out.println("receive: " + str);

            Map<String, Object> headers = msg.getMessageProperties().getHeaders();
            Iterator<Map.Entry<String, Object>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                System.out.println(next.getKey() + " --> " + next.getValue());
            }


        }
    }

}
