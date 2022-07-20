package per.study.juc.executor;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceExample4
{
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        // testInvokeAny();
        // testInvokeAnyTimeOut();
        // testInvokeAll();
        // testInvokeAllTimeOut();
        // testSubmitRunnable();
        testSubmitRunnableWithResult();
    }

    private static void testInvokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            System.out.println(Thread.currentThread().getName() + "_" + i);
            return i;
        }).collect(toList());

        Integer value = executorService.invokeAny(callableList);
        System.out.println("========finished========");
        System.out.println(value);
    }

    private static void testInvokeAnyTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Integer value = executorService.invokeAny(IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
            System.out.println(Thread.currentThread().getName() + "_" + i);
            return i;
        }).collect(toList()), 1, TimeUnit.SECONDS);
        System.out.println("========finished========");
        System.out.println(value);
    }

    private static void testInvokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "_" + i);
            return i;
        }).collect(toList());

        executorService.invokeAll(callableList).parallelStream().map(future -> {
            try {
                return future.get();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
        System.out.println("========finished========");
    }

    private static void testInvokeAllTimeOut() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.invokeAll(IntStream.range(0, 5).boxed().map(i -> (Callable<Integer>) () -> {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "_" + i);
            return i;
        }).collect(toList()), 1, TimeUnit.SECONDS).parallelStream().map(future -> {
            try {
                return future.get();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
        System.out.println("========finished========");
    }

    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> future = executorService.submit(new Runnable()
        {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Object o = future.get();
        System.out.println("R: " + o);
    }

    private static void testSubmitRunnableWithResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<String> future = executorService.submit(new Runnable()
        {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, result);

        System.out.println(future.get());

    }

}
