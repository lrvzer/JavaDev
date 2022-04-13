package per.study.juc.atomic.chapter2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean应用场景
 */
public class AtomicBooleanFlag {

    private static final AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag.get()) {
                try {
                    Thread.sleep(1000);
                    System.out.println("I am working.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("I am finished.");
        }).start();

        Thread.sleep(5000);
        flag.set(false);
    }
}
