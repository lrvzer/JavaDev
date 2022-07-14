package per.study.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample3 {

    /**
     * public void acquire()
     * public void acquire(int permits)
     * public void release()
     * public void release(int permits)
     *
     * public void acquireUninterruptibly()
     * public void acquireUninterruptibly(int permits)
     *
     * public int availablePermits()
     * public final int getQueueLength()
     */

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
            System.out.println("T1 finished.");
        });
        t1.start();

        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread(() -> {
            try {
                semaphore.acquireUninterruptibly();
            } finally {
                semaphore.release();
            }
            System.out.println("T2 finished.");
        });
        t2.start();

        TimeUnit.MILLISECONDS.sleep(50);

        t2.interrupt();

    }
}
