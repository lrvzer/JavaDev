package com.rabbitmq.queue.ttl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.factory.RabbitMQFactory;

/**
 * TTL - Time To Live
 */
public class QueueTTL {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("Exchange_TTL_60s", "direct", true, false, null);

//        Map<String, Object> prop = new HashMap<>();
//        prop.put("x-message-ttl", 20000);

        channel.queueDeclare("Queue_TTL_60s", true, false, false, null);
        channel.queueBind("Queue_TTL_60s", "Exchange_TTL_60s", "ttl");

        AMQP.BasicProperties map = new AMQP.BasicProperties()
                .builder()
                .expiration("7000")
                .build();
        channel.basicPublish("Exchange_TTL_60s", "ttl", map, "set_ttl".getBytes());

        channel.close();
        conn.close();
    }

}
