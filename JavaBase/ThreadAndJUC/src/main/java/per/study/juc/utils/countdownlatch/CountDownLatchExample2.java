package per.study.juc.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample2 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " do some initial working.");
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName() + " do other working.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程1").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " asyn prepare for some data.");
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " data prepare for done.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        }, "线程2").start();

        Thread.currentThread().join();

    }

}
