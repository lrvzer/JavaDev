package per.study.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample2 {

    /**
     * connection pool
     * <pre>
     * when get the available connection/policy
     * 1.Get 1000MS then throw exception
     * 2.blocking
     * 3.discard
     * 4.Get then throw exception
     * 5.get -> register the callback, -> call you.
     * </pre>
     */

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " in.");
                try {
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + " Get the semaphore.");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release(1);
                }
                System.out.println(Thread.currentThread().getName() + " out.");
            }).start();
        }

        while (true) {
            System.out.println("AP->" + semaphore.availablePermits());
            System.out.println("QL->" + semaphore.getQueueLength());
            System.out.println("===================");
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
