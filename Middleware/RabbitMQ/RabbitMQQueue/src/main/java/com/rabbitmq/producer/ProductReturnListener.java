package com.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.factory.RabbitMQFactory;

public class ProductReturnListener
{

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("Exchange_ReturnListener", "direct", true, false, null);
        channel.queueDeclare("Queue_ReturnListener", true, false, false, null);
        channel.queueBind("Queue_ReturnListener", "Exchange_ReturnListener", "ReturnListener");

        /* 添加确认监听 */
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println("replyCode: " + replyCode);
            System.out.println("replyText: " + replyText);
            System.out.println("exchange: " + exchange);
            System.out.println("routingKey: " + routingKey);
            System.out.println("properties: " + properties);
            System.out.println("body: " + new String(body));
        });

        /**
         *  mandatory
         *      true -- ReturnListener works
         */
        channel.basicPublish("Exchange_ReturnListener", "ReturnListener00", true, null, "ReturnListener".getBytes());

        Thread.sleep(2000L);

        channel.close();
        conn.close();
    }

}
