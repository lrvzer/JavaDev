package com.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.factory.RabbitMQFactory;

public class ProducerConfirm
{

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection conn = RabbitMQFactory.getConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare("Confirm_Model", true, false, false, null);

        channel.confirmSelect(); /* 生产者确认模式 */
        /* 添加监听 */
        channel.addConfirmListener((deliveryTag, multiple) -> {
            System.out.println("消息成功到达mq");
        }, (deliveryTag, multiple) -> {
            System.out.println("消息到达mq失败");
        });

        channel.basicPublish("", "Confirm_Model", null, "MessageReceive".getBytes());

        Thread.sleep(2000L);

        channel.close();
        conn.close();
    }

}
