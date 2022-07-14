package per.study.juc.executor;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 */
public class ThreadPoolExecutorTask {

    public static void main(String[] args) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                10,
                20,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> new Thread(r),
                new ThreadPoolExecutor.AbortPolicy()
        );

        IntStream.range(0, 20)
                .boxed()
                .forEach(i -> executorService.execute(
                        () -> {
                            try {
                                TimeUnit.SECONDS.sleep(10);
                                System.out.println("The work [" + Thread.currentThread().getName() + "] finished done.");
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ));

//        executorService.shutdown();
        List<Runnable> runnableList;
        try {
            runnableList = executorService.shutdownNow();
            executorService.awaitTermination(1, TimeUnit.HOURS);
            System.out.println("=======over=======");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===========");
        System.out.println(runnableList);
        System.out.println(runnableList.size());

        /**
         * shutdown
         *
         * 20 threads
         *      10 threads work
         *      10 idle
         *
         * shutdown invoked
         *
         * 1. 10 waiting to finish the work.
         * 2. 10 interrupted the idle works.
         * 3. 20 idle threads will exist.
         */

        /**
         * shutdownNow
         *
         * 10 threads queue elements 10
         * 10 running
         * 10 stored in the blocking queue.
         *
         * 1. return List<Runnable> remain 10 un handle runnable.
         * 2. interrupt all threads in the pool.
         */
    }

}
