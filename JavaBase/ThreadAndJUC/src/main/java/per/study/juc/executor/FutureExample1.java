package per.study.juc.executor;

import java.util.concurrent.*;

public class FutureExample1
{
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testGet();
        testGetTimeOut();
    }

    /**
     * {@link Future#get()}
     */
    private static void testGet() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });
        System.out.println("do something");
        String ret = (String) future.get();
        System.out.println(ret);
    }

    /**
     * {@link Future#get()}
     */
    private static void testGetTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });
        System.out.println("do something");
        Integer ret = (Integer) future.get(5, TimeUnit.SECONDS);
        System.out.println(ret);
    }
}
