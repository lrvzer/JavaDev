package com.rabbitmq.rabbitadmin;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRabbitMQBean
{

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Autowired
    Binding bindingDirect;

    @Autowired
    Binding bindingFanout;

    @Autowired
    Binding bindingTopic;

    @Autowired
    Binding bindingHeader;

    @Test
    public void declareBindingBean() {
        rabbitAdmin.declareBinding(bindingDirect);
        rabbitAdmin.declareBinding(bindingFanout);
        rabbitAdmin.declareBinding(bindingTopic);
        rabbitAdmin.declareBinding(bindingHeader);
    }


}
