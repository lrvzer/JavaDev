package per.study.juc.executor;

import java.util.concurrent.*;

public class ExecutorServiceExample2
{

    public static void main(String[] args) throws InterruptedException {
        // testAbortPolicy();
        // testDiscardPolicy();
        // testCallerRunsPolicy();
        testDiscardOldestPolicy();
    }

    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
    }

    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
        System.out.println("=============");
    }

    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("=============");
    }

    private static void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("=============");
    }

}
