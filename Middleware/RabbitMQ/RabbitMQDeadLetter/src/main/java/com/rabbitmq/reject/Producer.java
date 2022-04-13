package com.rabbitmq.reject;

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
        channel.exchangeDeclare("Exchange_Normal_20s", "direct", true, false, false, null);

        /*定义队列上的所有消息TTL为20s*/


        /*创建正常的队列*/
        Map<String, Object> queueProp = new HashMap<>();
        queueProp.put("x-message-ttl", 20000);
        queueProp.put("x-dead-letter-exchange", "Exchange_Deal_DeadLetter");

        channel.queueDeclare("Queue_Normal_20s", true, false, false, queueProp);
        channel.queueBind("Queue_Normal_20s", "Exchange_Normal_20s", "normal");

        byte[] proBuf = "有瑕疵".getBytes();
        channel.basicPublish("Exchange_Normal_20s", "normal", null, proBuf);

        channel.close();
        conn.close();
    }
}
