package com.rabbitmq.queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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

            // 声明一个队列
            /**
             * boolean durable      true --> 队列的持久化
             * boolean exclusive    true --> 独占
             *      同一时刻，只能有一个程序连接队列，独占队列必定是自动删除队列
             * boolean autoDelete   true --> 在第一次使用后，如果没有连接，并且队列里没有数据，队列会自动删除
             */
            channel.queueDeclare("Queue_Name", true, false, false, null);
            channel.queueDeclare("Queue_Non_Durable", false, false, false, null);
            channel.queueDeclare("Queue_Exclusive", false, true, false, null);

            // 通过信道发送数据
            byte[] buf = "hello, rabbitmq".getBytes();
            channel.basicPublish("", "Queue_Name", null, buf);

            Map<String, Object> headers = new HashMap<>();
            headers.put("name", "zhangSan");
            headers.put("phone", "1234567890");
            headers.put("address", "beiTing");

            AMQP.BasicProperties props = new AMQP.BasicProperties()
                    .builder()
                    .contentType("UTF-8")
                    .expiration("100000") /*过期时间100s*/
                    .deliveryMode(1) /* 消息持久化处理 1-不会持久化到磁盘  2-会持久化到磁盘 */
                    .headers(headers) /*自定义属性详情*/
                    .build();

            byte[] proBuf = "prop, testing".getBytes();
            channel.basicPublish("", "Queue_Name", props, proBuf);

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
