package per.study.juc.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorBuild {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;

        while (true) {
            if (activeCount != executorService.getActiveCount() || queueSize != executorService.getQueue().size()) {
                System.out.println("ActiveCount     -> " + executorService.getActiveCount());
                System.out.println("CorePoolSize    -> " + executorService.getCorePoolSize());
                System.out.println("QueueSize       -> " + executorService.getQueue().size());
                System.out.println("MaximumPoolSize -> " + executorService.getMaximumPoolSize());
                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
            }
        }
    }

    /**
     * Testing point.
     * <pre>
     *     1.coreSize = 1, maxSize = 2, blockingQueue is empty
     *       what will happen when submit 3 task?
     *     2.coreSize = 1, MaxSize = 2, blockingQueue size = 5
     *       what will happen when submit 7 task?
     *     3.coreSize = 1, MaxSize = 2, blockingQueue size = 5
     *       what will happen when submit 8 task?
     * </pre>
     *
     * params:
     * int corePoolSize
     * int maximumPoolSize
     * long keepAliveTime
     * TimeUnit unit
     * BlockingQueue<Runnable> workQueue
     * ThreadFactory threadFactory
     * RejectedExecutionHandler handler
     *
     * @return
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                2,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        System.out.println("The ThreadPoolExecutor create done.");

        executorService.execute(() -> sleepSeconds(100));
        executorService.execute(() -> sleepSeconds(10));
        executorService.execute(() -> sleepSeconds(10));

        return executorService;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
