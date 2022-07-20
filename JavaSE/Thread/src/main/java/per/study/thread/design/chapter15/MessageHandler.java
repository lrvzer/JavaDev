package per.study.thread.design.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(
                () -> {
                    String value = message.getValue();
                    try {
                        Thread.sleep(RANDOM.nextInt(1000));
                        System.out.println("The message [" + value + "] will be handled by" + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void shutdown() {
        ((ExecutorService)executor).shutdown();
    }

}
