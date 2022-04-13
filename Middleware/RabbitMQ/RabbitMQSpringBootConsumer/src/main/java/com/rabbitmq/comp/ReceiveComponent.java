package com.rabbitmq.comp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.domain.User;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ReceiveComponent
{

    // 监听队列
    @RabbitListener(queues = {"Spring_Queue"})
    @RabbitHandler
    public void onMessage(Message msg, Channel channel) {
        System.out.println("receive: " + msg.getPayload());
        long delivery = (long) msg.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(delivery, false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 使用复合注解创建队列、交换机并绑定
//    @RabbitListener(bindings = {
//            @QueueBinding(value = @Queue(value = "annotation_queue", durable = "true", exclusive = "false", autoDelete = "false"), exchange = @Exchange(value = "annotation_exchange", durable = "true", type = "direct"), key = "annotation_key") })
//    @RabbitHandler
//    public void onMessageNewQueue(Message msg, Channel channel) {
//        System.out.println("new queue receive: " + msg.getPayload());
//        long delivery = (long) msg.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
//        try {
//            channel.basicAck(delivery, false);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @RabbitListener(
            queues = {"annotation_queue"}
    )
    @RabbitHandler
    public void onMessageUser(@Payload User user, Channel channel, @Headers Map<String, Object> headers) {
        System.out.println("user message: " + user);
        long delivery = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            channel.basicAck(delivery, false);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
