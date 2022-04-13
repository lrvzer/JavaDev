package com.rabbitmq.limit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.factory.RabbitMQFactory;

/**
 * 消息已经过期
 * 消费者拒绝消息
 * 邮件队列长度已满
 */
public class Producer
{
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        /*创建处理死信的交换机*/
        channel.exchangeDeclare("Exchange_Deal_DeadLetter", "topic", true, false, false, null);
        /*创建处理死信的队列*/
        channel.queueDeclare("Queue_Deal_DeadLetter", true, false, false, null);
        channel.queueBind("Queue_Deal_DeadLetter", "Exchange_Deal_DeadLetter", "#");

        /*创建正常的交换机*/
        channel.exchangeDeclare("Exchange_Max_Length", "direct", true, false, false, null);

        Map<String, Object> queueProp = new HashMap<>();
        queueProp.put("x-max-length", 2);
        queueProp.put("x-dead-letter-exchange", "Exchange_Deal_DeadLetter");

        /*创建正常的队列*/
        channel.queueDeclare("Queue_Max_Length", true, false, false, queueProp);
        channel.queueBind("Queue_Max_Length", "Exchange_Max_Length", "normal");

        for (int i=0; i<3; i++) {
            byte[] proBuf = (i + " set max length").getBytes();
            channel.basicPublish("Exchange_Max_Length", "normal", null, proBuf);
        }

        channel.close();
        conn.close();
    }
}
