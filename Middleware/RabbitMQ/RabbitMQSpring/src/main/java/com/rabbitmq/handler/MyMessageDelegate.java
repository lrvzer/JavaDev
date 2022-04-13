package com.rabbitmq.handler;

import com.rabbitmq.domain.MyOrder;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * 处理消息数据
 */
public class MyMessageDelegate {

    /**
     * 默认处理消息的名称方法
     * @param body
     */
    public void handleMessage(byte[] body) {
        String msg = new String(body);
        System.out.println("receive : " + msg);
    }

    /**
     * 自定义名称的处理消息的方法
     * @param body
     */
    public void myHandleMessage(byte[] body) {
        String msg = new String(body);
        System.out.println("custom receive : " + msg);
    }

    public void handleHongMessage(byte[] body) {
        String msg = new String(body);
        System.out.println("Hong receive : " + msg);
    }

    public void handleXaoMessage(byte[] body) {
        String msg = new String(body);
        System.out.println("Xao receive : " + msg);
    }

    public void handleMessageByMessageConverter(String str) {
        System.out.println("MessageConverter receive : " + str);
    }

    /**
     * 转Map数据
     * @param data
     */
    public void handleMessageByJackson(Map data) {
        Iterator<Map.Entry<String, Object>> iterator = data.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey() + "--" + next.getValue());
        }
    }

    public void handleMessageByObject(MyOrder myOrder) {
        System.out.println(myOrder.getId());
        System.out.println(myOrder.getName());
        System.out.println(myOrder.getPrice());
    }

    public void handleMsgFile(File file) {
        System.out.println("handleMsgFile: " + file.getName());
    }


}
