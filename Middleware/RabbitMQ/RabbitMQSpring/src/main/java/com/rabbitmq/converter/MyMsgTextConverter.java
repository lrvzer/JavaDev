package com.rabbitmq.converter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class MyMsgTextConverter implements MessageConverter
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
        String contentType = message.getMessageProperties().getContentType();
        if (contentType != null && contentType.contains("text")) {
            return new String(message.getBody());
        } else {
            return message.getBody();
        }
    }
}
