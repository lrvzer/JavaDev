package com.rabbitmq.exchange.headers;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.Factory.RabbitMQFactory;
import com.rabbitmq.client.*;

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
                System.out.printf(properties.getHeaders().toString());
                String buf = new String(body);
                System.out.println(buf);
            }
        };

        channel.basicConsume("Queue_Header_All", true, consumer);
        channel.basicConsume("Queue_Header_Any", true, consumer);
        channel.close();
        conn.close();
    }
}
