package com.rabbitmq.exchange.headers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.Factory.RabbitMQFactory;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Topic
 */
public class Producer
{

    private static final String EXCHANGE_ANY = "Exchange_headers_Any";
    private static final String EXCHANGE_ALL = "Exchange_headers_All";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 连接RabbitMQ
        Connection conn = RabbitMQFactory.getConnection();

        // 创建信道
        Channel channel = conn.createChannel();

        // 声明headers型交换机
        channel.exchangeDeclare(EXCHANGE_ALL, "headers", true, false, null);
        channel.exchangeDeclare(EXCHANGE_ANY, "headers", true, false, null);

        channel.queueDeclare("Queue_Header_All", true, false, false, null);
        channel.queueDeclare("Queue_Header_Any", true, false, false, null);

        Map<String, Object> regular = new HashMap<>();
        regular.put("x-match", "all");
        regular.put("username", "Jobs");
        regular.put("password", "123456");
        regular.put("phone", "1234567890");
        channel.queueBind("Queue_Header_All", EXCHANGE_ALL, "", regular);

        regular.put("x-match", "any");
        channel.queueBind("Queue_Header_Any", EXCHANGE_ANY, "", regular);


        Map<String, Object> map = new HashMap<>();
        map.put("username", "Jobs");
        map.put("password", "123456");
        map.put("phone", "1234567890");
        AMQP.BasicProperties prop = new AMQP.BasicProperties()
                .builder()
                .contentType("UTF-8")
                .expiration("10000")
                .deliveryMode(2)
                .headers(map)
                .build();
        channel.basicPublish(EXCHANGE_ALL, "", prop, "match all".getBytes());
        channel.basicPublish(EXCHANGE_ANY, "", prop, "match Any".getBytes());

        channel.close();
        conn.close();
    }

}
