package per.study.juc.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorLongTimeTask {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                20,
                30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy()
        );

        IntStream.range(0, 10).boxed().forEach(
                i-> {
                    executor.submit(()-> {
                        while (true) {

                        }
                    });
                }
        );

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("======start the sequence working======");
    }
}
