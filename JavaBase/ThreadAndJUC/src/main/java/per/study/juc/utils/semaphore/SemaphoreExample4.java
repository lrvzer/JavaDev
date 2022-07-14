package per.study.juc.utils.semaphore;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample4 {
    public static void main(String[] args) throws InterruptedException {

        final MySemaphore semaphore = new MySemaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.drainPermits();
                System.out.println(Thread.currentThread().getName() + " AP-> " + semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release(5);
            }
        }, "T1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
//                semaphore.acquire();
//                boolean success = semaphore.tryAcquire();
                boolean success = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                System.out.println(success ? "success" : "failure");
                System.out.println(Thread.currentThread().getName() + " AP-> " + semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release(5);
            }
        }, "T2");
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(semaphore.hasQueuedThreads());
        Collection<Thread> waitingThreads = semaphore.getWaitingThread();
        for (Thread t : waitingThreads) {
            System.out.println(t);
        }
    }

    static class MySemaphore extends Semaphore {

        public MySemaphore(int permits) {
            super(permits);
        }

        public MySemaphore(int permits, boolean fair) {
            super(permits, fair);
        }

        public Collection<Thread> getWaitingThread() {
            return super.getQueuedThreads();
        }
    }

}
