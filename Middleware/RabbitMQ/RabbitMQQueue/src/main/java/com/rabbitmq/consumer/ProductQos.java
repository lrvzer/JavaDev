package com.rabbitmq.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.factory.RabbitMQFactory;

public class ProductQos
{

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("Exchange_Qos", "direct", true, false, null);
        channel.queueDeclare("Queue_Qos", true, false, false, null);
        channel.queueBind("Queue_Qos", "Exchange_Qos", "ReturnListener");

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
         * mandatory true -- ReturnListener works
         */
        for (int i = 0; i < 4; i++) {
            channel.basicPublish("Exchange_Qos", "ReturnListener", false, null, ("Qos" + i).getBytes());
        }

        Thread.sleep(2000L);

        channel.close();
        conn.close();
    }

}
