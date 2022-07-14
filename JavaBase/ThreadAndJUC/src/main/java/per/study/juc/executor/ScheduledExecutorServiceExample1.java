package per.study.juc.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ScheduledExecutorServiceExample1
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

        /*
         * ScheduledFuture<?> future = executor.schedule(() ->
         * System.out.println("======"), 2, TimeUnit.SECONDS);
         * System.out.println(future.cancel(true));
         */

        // ScheduledFuture<Integer> future = executor.schedule(() -> 2, 2,
        // TimeUnit.SECONDS);
        // System.out.println(future.get());

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

    }
}
