package per.study.juc.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * latch.await(...) 释放
 */
public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Thread thread = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            latch.countDown();
            thread.interrupt();

        }).start();

//        latch.await();
        latch.await(1000, TimeUnit.MILLISECONDS);
        System.out.println("==========");

    }

}
