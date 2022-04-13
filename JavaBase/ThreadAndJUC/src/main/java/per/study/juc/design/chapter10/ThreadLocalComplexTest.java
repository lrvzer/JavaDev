package per.study.juc.design.chapter10;

import java.util.Random;

public class ThreadLocalComplexTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("===========================");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
