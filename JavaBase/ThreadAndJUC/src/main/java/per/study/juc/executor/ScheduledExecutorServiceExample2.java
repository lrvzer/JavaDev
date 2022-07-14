package per.study.juc.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecutorServiceExample2
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
         test();
//        test1();
    }

    private static void test() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getExecuteExistingDelayedTasksAfterShutdownPolicy());
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
        /*
         * executor.scheduleWithFixedDelay(() -> { System.out.println("===" +
         * System.currentTimeMillis()); }, 1, 2, TimeUnit.SECONDS);
         */

        final AtomicLong interval = new AtomicLong(0L);
        executor.scheduleWithFixedDelay(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger at %d\n", currentTimeMillis);
            }
            else {
                System.out.printf("The actual spend [%d]\n", currentTimeMillis - interval.get());
            }
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 2, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
    }

    private static void test1() throws InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        System.out.println(executor.getContinueExistingPeriodicTasksAfterShutdownPolicy());
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        final AtomicLong interval = new AtomicLong(0L);
        executor.scheduleAtFixedRate(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            if (interval.get() == 0) {
                System.out.printf("The first time trigger at %d\n", currentTimeMillis);
            }
            else {
                System.out.printf("The actual spend [%d]\n", currentTimeMillis - interval.get());
            }
            interval.set(currentTimeMillis);
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, 2, TimeUnit.SECONDS);
        TimeUnit.MILLISECONDS.sleep(1200);
        executor.shutdown();
    }
}
