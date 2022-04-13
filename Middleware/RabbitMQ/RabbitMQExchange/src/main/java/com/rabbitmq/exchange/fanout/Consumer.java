package com.rabbitmq.exchange.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import com.rabbitmq.Factory.RabbitMQFactory;

public class Consumer
{
    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接RabbitMQ
        Connection conn = RabbitMQFactory.getConnection();
        // 创建信道
        Channel channel = conn.createChannel();

        DefaultConsumer consumer = new DefaultConsumer(channel)
        {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String buf = new String(body);
                System.out.println(buf);
            }
        };

        channel.basicConsume("Queue_Fanout", true, consumer);
        channel.close();
        conn.close();
    }
}
