package com.rabbitmq.rabbittemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.domain.MyOrder;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
class SendMethodTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void testSendToDirect() {
        String loginSms = "欢迎登陆";
        rabbitTemplate.convertAndSend("Spring_Direct_Exchange", "direct", loginSms, message -> {
            message.getMessageProperties().getHeaders().put("code", 1);
            return message;
        });
    }

    @Test
    void testSendToFanout() {
        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("UTF-8");
        properties.getHeaders().put("code", 2);

        Message message = new Message("Thank you".getBytes(), properties);

        rabbitTemplate.send("Spring_Fanout_Exchange", "", message);
    }

    @Test
    void testSendToTopic() {
        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("UTF-8");
        properties.getHeaders().put("code", 3);
        Message message1 = new Message("topic".getBytes(), properties);
        rabbitTemplate.send("Spring_Topic_Star_Exchange", "0086.15252042275", message1);

        properties.getHeaders().put("username", "Huawei");
        properties.getHeaders().put("password", "123456");
        Message message2 = new Message("header".getBytes(), properties);
        rabbitTemplate.send("Spring_Header_Exchange", "", message2);
    }

    @Test
    void sendMessageToHongXao() {
        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("UTF-8");
        properties.getHeaders().put("code", 3);
        Message message1 = new Message("HongMi Phone".getBytes(), properties);
        rabbitTemplate.send("", "Spring_Hong_Queue", message1);

        properties.getHeaders().put("username", "Huawei");
        properties.getHeaders().put("password", "123456");
        Message message2 = new Message("XiaoMi Phone".getBytes(), properties);
        rabbitTemplate.send("", "Spring_Xao_Queue", message2);
    }

    @Test
    void sendMessageJson() {
        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("UTF-8");
        properties.setContentType("application/json");
        properties.getHeaders().put("__TypeId__", "com.rabbitmq.domain.MyOrder");

        MyOrder order = new MyOrder();
        order.setId(1);
        order.setName("小米10Pro");
        order.setPrice(1999);

        // 将普通对象转JSON形式的字符串
        ObjectMapper om = new ObjectMapper();
        String str = "";
        try {
            str = om.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Message msg = new Message(str.getBytes(), properties);
        rabbitTemplate.send("", "Spring_Hong_Queue", msg);
    }

    @Test
    void sendFileToRabbit() {
        MessageProperties prop = new MessageProperties();
        prop.setContentType("my-jpg");
        prop.getHeaders().put("extName", "jpg");

        File fileJpg = new File("/Users/Lrwei/Lrwei/IdeaProjects/Middleware/RabbitMQ/RabbitMQSpring/file/dog.jpg");
        try {
            byte[] bytes = Files.readAllBytes(fileJpg.toPath());
            Message msg = new Message(bytes, prop);
            rabbitTemplate.send("", "Spring_Hong_Queue", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File filePdf = new File("/Users/Lrwei/Lrwei/IdeaProjects/Middleware/RabbitMQ/RabbitMQSpring/file/snake.pdf");
        prop.setContentType("my-pdf");
        prop.getHeaders().put("extName", "pdf");
        try {
            byte[] bytes = Files.readAllBytes(filePdf.toPath());
            Message msg = new Message(bytes, prop);
            rabbitTemplate.send("", "Spring_Hong_Queue", msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
