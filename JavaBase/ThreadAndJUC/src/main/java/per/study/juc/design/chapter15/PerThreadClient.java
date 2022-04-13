package per.study.juc.design.chapter15;

import java.util.stream.IntStream;

/**
 * 一个请求一个线程
 */
public class PerThreadClient {

    public static void main(String[] args) {
        final MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10)
                .forEach(
                        i -> handler.request(new Message(String.valueOf(i)))
                );

        handler.shutdown();
    }

}
