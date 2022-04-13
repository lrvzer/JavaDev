package com.rabbitmq.rabbitadmin;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringRabbitMQ
{

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Test
    void bindExchangeAndQueue() {
        Queue queue = new Queue("Spring_Create_Queue", true, false, false, null);
        rabbitAdmin.declareQueue(queue);

        // DirectExchange
        Exchange directExchange = new DirectExchange("Spring_Direct_Exchange", true, false, null);
        rabbitAdmin.declareExchange(directExchange);

        // FanoutExchange
        Exchange fanoutExchange = new FanoutExchange("Spring_Fanout_Exchange", true, false, null);
        rabbitAdmin.declareExchange(fanoutExchange);

        // TopicExchange
        Exchange topicExchangeStar = new TopicExchange("Spring_Topic_Star_Exchange", true, false, null);
        rabbitAdmin.declareExchange(topicExchangeStar);

        // HeadersExchange
        Exchange headerExchange = new HeadersExchange("Spring_Header_Exchange", true, false, null);
        rabbitAdmin.declareExchange(headerExchange);

        // 绑定1
        // Binding binding = new Binding("Spring_Create_Queue",
        // Binding.DestinationType.QUEUE, "Spring_Create_Exchange", "Spring", null);
        // rabbitAdmin.declareBinding(binding);

        // 绑定2
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with("Spring").and(null));
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(fanoutExchange).with("").and(null));
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(topicExchangeStar).with("0086.*").and(null));

        Map<String, Object> prop = new HashMap<>();
        prop.put("x-match", "all");
        prop.put("username", "Hwei");
        prop.put("password", "123456");

        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(headerExchange).with("").and(prop));
    }

    /**
     * 删除队列和交换机
     */
    @Test
    void deleteQueueAndExchange() {
        // 删除队列
        rabbitAdmin.deleteQueue("Spring_Create_Queue");

        // 删除交换机
        rabbitAdmin.deleteExchange("Spring_Direct_Exchange");
        rabbitAdmin.deleteExchange("Spring_Fanout_Exchange");
        rabbitAdmin.deleteExchange("Spring_Topic_Star_Exchange");
        rabbitAdmin.deleteExchange("Spring_Header_Exchange");
    }

}
