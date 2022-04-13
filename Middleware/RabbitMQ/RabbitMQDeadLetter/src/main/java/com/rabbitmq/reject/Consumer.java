package com.rabbitmq.reject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import com.rabbitmq.factory.RabbitMQFactory;

/**
 * 消费者拒绝消息
 */
public class Consumer
{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

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
                System.out.println("receive: " + buf);
                getChannel().basicReject(envelope.getDeliveryTag(), false);
            }
        };

        /*接收死信队列中的数据*/
        channel.basicConsume("Queue_Normal_20s", false, consumer);

        Thread.sleep(2000);
        channel.close();
        conn.close();
    }
}
