package com.rabbitmq.exchange.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.Factory.RabbitMQFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Topic
 */
public class Producer
{

    private static final String EXCHANGE = "Exchange_Topic*";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 连接RabbitMQ
        Connection conn = RabbitMQFactory.getConnection();

        // 创建信道
        Channel channel = conn.createChannel();

        // 声明Topic型交换机
        channel.exchangeDeclare(EXCHANGE, "topic", true, false, null);

        // 声明队列
        channel.queueDeclare("Queue_Topic*", true, false, false, null);

        // 绑定  queue bind exchange
        // # 可以匹配多个单词或0个  #.topic  -->  word.topic  /  .topic  /  word1.word2.topic
//        channel.queueBind("Queue_Topic#", EXCHANGE, "#.topic");
        // * 可以匹配一个单词或者0个单词  word.topic  /  .topic
        channel.queueBind("Queue_Topic*", EXCHANGE, "*.topic");

        String buf = "*.topic";
        channel.basicPublish(EXCHANGE, "a.topic", null, buf.getBytes());

        channel.close();

        conn.close();
    }

}
