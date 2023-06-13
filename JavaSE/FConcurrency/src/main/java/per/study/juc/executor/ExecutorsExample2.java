package per.study.juc.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorsExample2
{

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        // Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);

        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i -> (Callable<String>) () -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is working.");
            sleep(2);
            return "Task=" + i;
        }).collect(Collectors.toList());

        executorService.invokeAll(callableList).stream().map(future -> {
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

    }

    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
