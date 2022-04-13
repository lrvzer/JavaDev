package com.rabbitmq.exchange.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.Factory.RabbitMQFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Fanout 废弃routeKey
 */
public class Producer
{

    private static final String EXCHANGE = "Exchange_Fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = RabbitMQFactory.getConnection();

        // 连接RabbitMQ
        // 创建信道
        Channel channel = conn.createChannel();

        // 直连型交换机
        channel.exchangeDeclare(EXCHANGE, "fanout", true, false, null);

        // 队列
        channel.queueDeclare("Queue_Fanout", true, false, false, null);

        // 绑定
        channel.queueBind("Queue_Fanout", EXCHANGE, "code_direct");

        channel.basicPublish(EXCHANGE, "code_direct", null, "hello, exchange".getBytes());
        channel.basicPublish(EXCHANGE, "code_direct2", null, "hello, exchange2".getBytes());

        channel.close();

        conn.close();
    }

}
