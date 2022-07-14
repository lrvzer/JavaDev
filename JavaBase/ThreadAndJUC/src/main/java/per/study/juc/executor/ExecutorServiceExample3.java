package per.study.juc.executor;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceExample3
{
    public static void main(String[] args) throws InterruptedException {
        // test();
        // testAllowCoreThreadTimeOut();
        // testRemove();
        // testPreStartCoreThread();
        // testPreStartAllCoreThreads();
        testThreadPoolAdvice();
    }

    private static void test() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executor.getActiveCount());
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(executor.getActiveCount());
    }

    private static void testAllowCoreThreadTimeOut() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(true);
        IntStream.range(0, 5).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private static void testRemove() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.setKeepAliveTime(10, TimeUnit.SECONDS);
        executor.allowCoreThreadTimeOut(true);
        IntStream.range(0, 2).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("===========finished==========");
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        TimeUnit.MILLISECONDS.sleep(20);

        Runnable r = new Runnable()
        {
            @Override
            public void run() {
                System.out.println("will not be executed.");
            }
        };
        executor.execute(r);
        TimeUnit.MILLISECONDS.sleep(20);
        executor.remove(r);
    }

    private static void testPreStartCoreThread() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("===========finished==========");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("===========finished==========");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(executor.prestartCoreThread());
        System.out.println(executor.getActiveCount());
    }

    private static void testPreStartAllCoreThreads() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.setMaximumPoolSize(3);
        System.out.println(executor.prestartAllCoreThreads());
        System.out.println(executor.getActiveCount());
    }

    private static void testThreadPoolAdvice() {
        ThreadPoolExecutor executorService = new MyThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());
        executorService.execute(new MyRunnable(1)
        {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
    }

    private static class MyRunnable implements Runnable
    {

        private final int no;

        public MyRunnable(int no) {
            this.no = no;
        }

        protected int getData() {
            return this.no;
        }

        @Override
        public void run() {

        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor
    {

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("init the " + ((MyRunnable) r).getData());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (null == t) {
                System.out.println("successful  " + ((MyRunnable) r).getData());
            }
            else {
                t.printStackTrace();
            }
        }
    }

}
