package com.rabbitmq.comp;

import com.rabbitmq.domain.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class SenderComponent {

    @Autowired
    RabbitTemplate rabbitTemplate;

    RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        if (ack) {
            System.out.println("发送到MQ出现问题，此时应更新数据库，失败的原因：" + cause + "，错误的消息ID = " + correlationData);
        } else {
            System.out.println("消息发送成功，原因：" + cause + "，信息ID = " + correlationData);
        }

    };

    RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        System.out.println("replyCode-" + replyCode);
        System.out.println("replyText-" + replyText);
        System.out.println("exchange-" + exchange);
        System.out.println("routingKey-" + routingKey);
    };

    public void send(Object obj, Map<String, Object> props) {
        MessageHeaders headers = new MessageHeaders(props);
        Message msg = MessageBuilder.createMessage(obj, headers);
        String id = UUID.randomUUID().toString();
        CorrelationData cd = new CorrelationData(id);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("Spring_Direct_Exchange", "direct", msg, cd);
    }

    public void send(User user) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        String id = UUID.randomUUID().toString();
        CorrelationData cd = new CorrelationData(id);
        rabbitTemplate.convertAndSend("annotation_exchange", "annotation_key", user, cd);
    }

}
