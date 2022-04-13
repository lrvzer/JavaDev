package com.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.factory.RabbitMQFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class SetMaxLength {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        Map<String, Object> prop = new HashMap<>();
        prop.put("x-max-length", 2);
        channel.queueDeclare("Set_Max_length", true, false, false, prop);

        channel.basicPublish("", "Set_Max_length", null, "hello".getBytes());
        channel.basicPublish("", "Set_Max_length", null, "world".getBytes());
        channel.basicPublish("", "Set_Max_length", null, "thank".getBytes());

        channel.close();
        conn.close();
    }

}
