package com.rabbitmq.config;

import com.rabbitmq.converter.MyMsgJpgConverter;
import com.rabbitmq.converter.MyMsgPdfConverter;
import com.rabbitmq.converter.MyMsgTextConverter;
import com.rabbitmq.handler.MyMessageDelegate;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQListenerConfigure {

    private int num = 0;

    @Bean
    public Queue queueHong() {
        return new Queue("Spring_Hong_Queue", true, false, false, null);
    }

    @Bean
    public Queue queueXao() {
        return new Queue("Spring_Xao_Queue", true, false, false, null);
    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
//        SimpleMessageListenerContainer simpleListener =
//                new SimpleMessageListenerContainer(connectionFactory);
//
//        // 同时监听多个队列
//        simpleListener.setQueues(queueHong, queueXao, queueSMS);
//        // 一个消费者对象同时监听几个队列
//        simpleListener.setConcurrentConsumers(1);
//        // 最多创建几个消费者对象
//        simpleListener.setMaxConcurrentConsumers(5);
//        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
//        simpleListener.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        // 标签
//        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String consumerTag) {
//                consumerTag += "-" + num ++;
//                return consumerTag;
//            }
//        });
//
//        /**设置监听器
//         * MessageListener
//         * ChannelAwareMessageListener
//         */
////        simpleListener.setMessageListener(new MessageListener() {
////            @Override
////            public void onMessage(Message message) {
////                String s = new String(message.getBody());
////                System.out.println("receive message : " + s);
////                Map<String, Object> headers =
////                        message.getMessageProperties().getHeaders();
////                Iterator<Map.Entry<String, Object>> iterator = headers.entrySet().iterator();
////                while (iterator.hasNext()) {
////                    Map.Entry<String, Object> next = iterator.next();
////                    System.out.println(next.getKey() + "--" + next.getValue());
////                }
////            }
////        });
//
//        simpleListener.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                String s = new String(message.getBody());
//                System.out.println("receive message : " + s);
//                Map<String, Object> headers =
//                        message.getMessageProperties().getHeaders();
//                Iterator<Map.Entry<String, Object>> iterator = headers.entrySet().iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<String, Object> next = iterator.next();
//                    System.out.println(next.getKey() + "--" + next.getValue());
//                }
//
//                // 对消息进行手动确认
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//                // Nack增加了重回队列的参数，即最后一个参数：true-重回队列 false-不用重回
////                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            }
//        });
//
//        return simpleListener;
//    }

    /**使用监听适配器对消息进行接收和消费
     * @param connectionFactory
     * @param queueHong
     * @param queueXao
     * @param queueSMS
     * @return
     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
//        SimpleMessageListenerContainer simpleListener =
//                new SimpleMessageListenerContainer(connectionFactory);
//
//        // 同时监听多个队列
//        simpleListener.setQueues(queueHong, queueXao, queueSMS);
//        // 一个消费者对象同时监听几个队列
//        simpleListener.setConcurrentConsumers(1);
//        // 最多创建几个消费者对象
//        simpleListener.setMaxConcurrentConsumers(5);
//        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
//        simpleListener.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        // 标签
//        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String consumerTag) {
//                consumerTag += "-" + num ++;
//                return consumerTag;
//            }
//        });
//
//
//        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
//        listenerAdapter.setDelegate(new MyMessageDelegate());
//
//        /**
//         * MessageListenerAdapter --> setDefaultListenerMethod
//         *  可以设置自定义消息处理方法
//         * @see MyMessageDelegate#myHandleMessage(byte[])
//         *
//         *  不设置则默认为handleMessage
//         * @see MyMessageDelegate#handleMessage(byte[])
//         */
//        listenerAdapter.setDefaultListenerMethod("myHandleMessage");
//
//        simpleListener.setMessageListener(listenerAdapter);
//
//        return simpleListener;
//    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
//        SimpleMessageListenerContainer simpleListener =
//                new SimpleMessageListenerContainer(connectionFactory);
//
//        // 同时监听多个队列
//        simpleListener.setQueues(queueHong, queueXao, queueSMS);
//        // 一个消费者对象同时监听几个队列
//        simpleListener.setConcurrentConsumers(1);
//        // 最多创建几个消费者对象
//        simpleListener.setMaxConcurrentConsumers(5);
//        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
//        simpleListener.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        // 标签
//        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String consumerTag) {
//                consumerTag += "-" + num ++;
//                return consumerTag;
//            }
//        });
//
//
//        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
//        listenerAdapter.setDelegate(new MyMessageDelegate());
//
//        // queueMethod 用于设置队列名key，关联方法名value
//        Map<String, String> queueMethod = new HashMap<>();
//        /**
//         * @see MyMessageDelegate#handleHongMessage(byte[])
//         */
//        queueMethod.put("Spring_Hong_Queue", "handleHongMessage");
//        /**
//         * @see MyMessageDelegate#handleXaoMessage(byte[])
//         */
//        queueMethod.put("Spring_Xao_Queue", "handleXaoMessage");
//        listenerAdapter.setQueueOrTagToMethodName(queueMethod);
//
//        simpleListener.setMessageListener(listenerAdapter);
//
//        return simpleListener;
//    }


//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
//        SimpleMessageListenerContainer simpleListener =
//                new SimpleMessageListenerContainer(connectionFactory);
//
//        // 同时监听多个队列
//        simpleListener.setQueues(queueHong, queueXao, queueSMS);
//        // 一个消费者对象同时监听几个队列
//        simpleListener.setConcurrentConsumers(1);
//        // 最多创建几个消费者对象
//        simpleListener.setMaxConcurrentConsumers(5);
//        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
//        simpleListener.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        // 标签
//        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String consumerTag) {
//                consumerTag += "-" + num ++;
//                return consumerTag;
//            }
//        });
//
//
//        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
//        listenerAdapter.setDelegate(new MyMessageDelegate());
//        listenerAdapter.setMessageConverter(new MyMsgTextConverter());
//        listenerAdapter.setDefaultListenerMethod("handleMessageByMessageConverter");
//        simpleListener.setMessageListener(listenerAdapter);
//
//        return simpleListener;
//    }


//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
//        SimpleMessageListenerContainer simpleListener =
//                new SimpleMessageListenerContainer(connectionFactory);
//
//        // 同时监听多个队列
//        simpleListener.setQueues(queueHong, queueXao, queueSMS);
//        // 一个消费者对象同时监听几个队列
//        simpleListener.setConcurrentConsumers(1);
//        // 最多创建几个消费者对象
//        simpleListener.setMaxConcurrentConsumers(5);
//        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
//        simpleListener.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        // 标签
//        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String consumerTag) {
//                consumerTag += "-" + num ++;
//                return consumerTag;
//            }
//        });
//
//
//        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
//        listenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
//        listenerAdapter.setDelegate(new MyMessageDelegate());
//        listenerAdapter.setDefaultListenerMethod("handleMessageByJackson");
//        simpleListener.setMessageListener(listenerAdapter);
//
//        return simpleListener;
//    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
//        SimpleMessageListenerContainer simpleListener =
//                new SimpleMessageListenerContainer(connectionFactory);
//
//        // 同时监听多个队列
//        simpleListener.setQueues(queueHong, queueXao, queueSMS);
//        // 一个消费者对象同时监听几个队列
//        simpleListener.setConcurrentConsumers(1);
//        // 最多创建几个消费者对象
//        simpleListener.setMaxConcurrentConsumers(5);
//        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
//        simpleListener.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        // 标签
//        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
//            @Override
//            public String createConsumerTag(String consumerTag) {
//                consumerTag += "-" + num ++;
//                return consumerTag;
//            }
//        });
//
//
//        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
//        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        typeMapper.setTrustedPackages("*");
//        converter.setJavaTypeMapper(typeMapper);
//        listenerAdapter.setMessageConverter(converter);
//
//        listenerAdapter.setDelegate(new MyMessageDelegate());
//        listenerAdapter.setDefaultListenerMethod("handleMessageByObject");
//        simpleListener.setMessageListener(listenerAdapter);
//
//        return simpleListener;
//    }

        @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, Queue queueHong, Queue queueXao, Queue queueSMS) {
        SimpleMessageListenerContainer simpleListener =
                new SimpleMessageListenerContainer(connectionFactory);

        // 同时监听多个队列
        simpleListener.setQueues(queueHong, queueXao, queueSMS);
        // 一个消费者对象同时监听几个队列
        simpleListener.setConcurrentConsumers(1);
        // 最多创建几个消费者对象
        simpleListener.setMaxConcurrentConsumers(5);
        // 设置收到信息后的确认模式：自动模式AUTO; 手动模式MANUAL
        simpleListener.setAcknowledgeMode(AcknowledgeMode.AUTO);
        // 标签
        simpleListener.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String consumerTag) {
                consumerTag += "-" + num ++;
                return consumerTag;
            }
        });


        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();

        // 设置消息转换器
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        ContentTypeDelegatingMessageConverter globalConverter = new ContentTypeDelegatingMessageConverter();

        globalConverter.addDelegate("text", new MyMsgTextConverter());
        globalConverter.addDelegate("application/json", jsonConverter);
        globalConverter.addDelegate("my-jpg", new MyMsgJpgConverter());
        globalConverter.addDelegate("my-pdf", new MyMsgPdfConverter());

        listenerAdapter.setMessageConverter(globalConverter);

        // 设置消息处理类和方法
        listenerAdapter.setDelegate(new MyMessageDelegate());
        listenerAdapter.setDefaultListenerMethod("handleMsgFile");
        simpleListener.setMessageListener(listenerAdapter);

        return simpleListener;
    }


}
