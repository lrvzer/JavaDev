package com.rabbitmq.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer
{
    public static void main(String[] args) {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection conn = null;
        Channel channel = null;
        try {
            // 连接RabbitMQ
            conn = factory.newConnection();
            // 创建信道
            channel = conn.createChannel();

            // 直连型交换机
            channel.exchangeDeclare("Exchange_Direct", "direct", true, false, null);

            // 队列
            channel.queueDeclare("Queue_Direct", true, false, false, null);

            // 绑定
            channel.queueBind("Queue_Direct", "Exchange_Direct", "code_direct");

            channel.basicPublish("Exchange_Direct", "code_direct", null, "hello, exchange".getBytes());
            channel.basicPublish("Exchange_Direct", "code_direct2", null, "hello, exchange2".getBytes());
        }
        catch (Exception e) {
            throw new RuntimeException("rabbitmq 连接失败");
        }
        finally {
            if (channel != null) {
                try {
                    channel.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
