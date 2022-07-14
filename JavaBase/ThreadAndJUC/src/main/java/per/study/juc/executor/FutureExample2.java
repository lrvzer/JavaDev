package per.study.juc.executor;

import java.util.concurrent.*;

public class FutureExample2
{
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testIsDone();
//        testCancel();
        testCancel2();
    }

    /**
     * Completion may be due to normal termination, an exception, or cancellation
     * {@link Future#isDone()}
     */
    private static void testIsDone() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            }
//            catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return 10;
            throw new RuntimeException();
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(future.isDone());
        }
    }

    private static void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
//        System.out.println(future.get());
        System.out.println(future.isCancelled());
    }


    private static void testCancel2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
        Future<Integer> future = executorService.submit(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("===============");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            while (!Thread.interrupted()) {

            }
            System.out.println("11111");
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(future.get());
    }
}