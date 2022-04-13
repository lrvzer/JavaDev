package com.rabbitmq.consumer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import com.rabbitmq.consumer.customclass.ConsumerCustom;
import com.rabbitmq.factory.RabbitMQFactory;

public class ConsumerQos
{
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        channel.basicQos(0, 2, false);
        Consumer consumer = new ConsumerCustom(channel) ;
        /* autoAck - 消费时必须取消自动确认 */
        channel.basicConsume("Queue_Qos", false, consumer);

        Thread.sleep(2000L);
        channel.close();
        conn.close();
    }
}
