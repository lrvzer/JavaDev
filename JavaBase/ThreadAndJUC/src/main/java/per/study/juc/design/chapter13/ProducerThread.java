package per.study.juc.design.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread{

    private final MessageQueue messageQueue;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue, int seq) {
        super("PRODUCER-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("Message-" + COUNTER.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message);
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
