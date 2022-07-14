package per.study.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorsExample1
{
    public static void main(String[] args) throws InterruptedException {
//        useCachedThreadPool();
//        useFixedThreadPool();
        useSinglePool();
    }

    /**
     * newSingleThreadExecutor VS one Thread
     *
     * <p>
     * Thread will die after finish work, but SingleThreadExecutor can always alive.
     * Thread can not put the submitted runnable to the cache queue, but
     * SingleThreadExecutor can do.
     *
     *
     * new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new
     * LinkedBlockingQueue<Runnable>()) == Executors.newFixedThreadPool(1)
     *
     */
    private static void useSinglePool() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(50);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new
     * LinkedBlockingQueue<Runnable>());
     */
    private static void useFixedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(50);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new
     * SynchronousQueue<Runnable>());
     */
    private static void useCachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> System.out.println("======="));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(50);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " [" + i + "]");
        }));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

}
