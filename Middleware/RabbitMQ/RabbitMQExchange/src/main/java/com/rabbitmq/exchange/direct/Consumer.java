package com.rabbitmq.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer
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

            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String buf = new String(body);
                    System.out.println(buf);
                }
            };

            channel.basicConsume("Queue_Direct", true, consumer);

        }
        catch (Exception e) {
            throw new RuntimeException("rabbitmq 连接失败");
        }
        finally {
//            if (channel != null) {
//                try {
//                    channel.close();
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if (conn != null) {
//                try {
//                    conn.close();
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
}
