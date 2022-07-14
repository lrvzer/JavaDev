package per.study.juc.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ExecutorServiceExample1
{
    public static void main(String[] args) throws InterruptedException {
        // isShutDown();
        // isTerminated();
//        executeRunnableError();
        executeRunnableTask();
    }

    /**
     * 问题：当调用shutdown()方法后，可以再去执行新的runnable？ 不可以执行，新任务会被拒绝
     * {@link ExecutorService#shutdown()} {@link ExecutorService#isShutdown()}
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        executorService.execute(() -> System.out.println("after shutdown execute."));
    }

    /**
     * {@link ExecutorService#isTerminated()}
     * {@link ThreadPoolExecutor#isTerminating()}
     */
    private static void isTerminated() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
        System.out.println(((ThreadPoolExecutor) executorService).isTerminating());
    }

    private static void executeRunnableError() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(() -> System.out.println(1 / 0)));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("============");
    }

    private static void executeRunnableTask() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> executorService.execute(new MyTask(i)
        {
            @Override
            protected void error(Throwable cause) {
                System.out.println("The no: " + i + " failed, update status to ERROR.");
            }

            @Override
            protected void done() {
                System.out.println("The no: " + i + " successfully, update status to DONE.");
            }

            @Override
            protected void doExecute() {
                if (i % 3 == 0) {
                    int tmp = i / 0;
                }
            }

            @Override
            protected void doInit() {
                // do noting.
            }
        }));
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("============");
    }

    private static class MyTask implements Runnable
    {
        protected final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            }
            catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected void error(Throwable cause) {
        }

        protected void done() {
        }

        protected void doExecute() {
        }

        protected void doInit() {
        }
    }

    private static class MyThreadFactory implements ThreadFactory
    {
        private static final AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println(t.getName() + " execute failed.");
                cause.printStackTrace();
                System.out.println("==========");
            });
            return thread;
        }
    }

}
