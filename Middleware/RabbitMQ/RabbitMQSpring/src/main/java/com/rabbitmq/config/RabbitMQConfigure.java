package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfigure
{

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        return factory;
    }

    /**
     * 管理交换机和队列
     * 
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queueSMS() {
        return new Queue("Spring_Queue", true, false, false, null);
    }

    @Bean
    public Exchange directExchange() {
        return new DirectExchange("Spring_Direct_Exchange", true, false, null);
    }

    @Bean
    public Exchange fanoutExchange() {
        return new FanoutExchange("Spring_Fanout_Exchange", true, false, null);
    }

    @Bean
    public Exchange topicExchange() {
        return new TopicExchange("Spring_Topic_Star_Exchange", true, false, null);
    }

    @Bean
    public Exchange headersExchange() {
        return new HeadersExchange("Spring_Header_Exchange", true, false, null);
    }

    @Bean
    public Binding bindingDirect(Queue queueSMS, @Qualifier("directExchange")
    Exchange directExchange) {
        return BindingBuilder.bind(queueSMS).to(directExchange).with("direct").and(null);
    }

    @Bean
    public Binding bindingFanout(Queue queueSMS, @Qualifier("fanoutExchange")
    Exchange fanoutExchange) {
        return BindingBuilder.bind(queueSMS).to(fanoutExchange).with("").and(null);
    }

    @Bean
    public Binding bindingTopic(Queue queueSMS, @Qualifier("topicExchange")
    Exchange topicExchange) {
        return BindingBuilder.bind(queueSMS).to(topicExchange).with("0086.*").and(null);
    }

    @Bean
    public Binding bindingHeader(Queue queueSMS, @Qualifier("headersExchange")
    Exchange headersExchange) {
        Map<String, Object> prop = new HashMap<>();
        prop.put("x-match", "all");
        prop.put("username", "Huawei");
        prop.put("password", "123456");
        return BindingBuilder.bind(queueSMS).to(headersExchange).with("").and(prop);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

}
