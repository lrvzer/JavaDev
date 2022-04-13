package com.rabbitmq.converter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class MyMsgJpgConverter implements MessageConverter
{
    /**
     * Java对象转Message对象
     * 
     * @param o
     * @param messageProperties
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(o.toString().getBytes(), messageProperties);
    }

    /**
     * Message对象转Java对象
     * 
     * @param message
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String extName = (String) message.getMessageProperties().getHeaders().get("extName");
        String filePath = "/Users/Lrwei/Lrwei/IdeaProjects/Middleware/RabbitMQ/RabbitMQSpring/file/" + UUID.randomUUID() + "." + extName;
        File file = new File(filePath);
        try {
            Files.copy(new ByteArrayInputStream(message.getBody()), file.toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
