package per.study.juc.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureExample1
{

    public static void main(String[] args) {
        // futureDone();
//        completable();

        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
                .map((Function<Integer, Callable<Integer>>) integer -> () -> get()).collect(Collectors.toList());
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            executorService.invokeAll(tasks).stream().map(future -> {
                try {
                    return future.get();
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }).parallel().forEach(CompletableFutureExample1::display);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void futureDone() {
        ExecutorService service = Executors.newFixedThreadPool(10);

        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        while (!future.isDone()) {

        }

        System.out.println("DONE");
    }

    private static void completable() {
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).whenComplete((v, t) -> System.out.println("DONE"));

        try {
            Thread.currentThread().join();
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " display will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " display execute done " + data);
    }

    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " execute done " + value);
        return value;
    }

}
