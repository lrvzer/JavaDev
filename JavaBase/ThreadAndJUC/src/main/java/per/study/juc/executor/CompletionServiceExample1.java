package per.study.juc.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample1
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // futureDefect1();
        futureDefect2();
    }

    /**
     * No callback
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void futureDefect1() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        });

        System.out.println("=======");

        future.get();
    }

    private static void futureDefect2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callableList = Arrays.asList(() -> {
            sleep(10);
            System.out.println("the 10 finished.");
            return 10;
        }, () -> {
            sleep(20);
            System.out.println("the 20 finished.");
            return 20;
        });

        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(executorService.submit(callableList.get(0)));
        futures.add(executorService.submit(callableList.get(1)));
        for (Future<Integer> fu : futures) {
            System.out.println(fu.get());
        }
    }

    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
