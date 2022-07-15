package per.study.juc.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample5
{
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // getNow();
        // complete();
        // join();
        // completeExceptionally();
        // obtrudeException();
        CompletableFuture<String> future = errorHandle();
        future.whenComplete((v, t) -> System.out.println(v));
        Thread.currentThread().join();
    }

    private static CompletableFuture<String> errorHandle() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("==========");
            return "Hello";
        });
        future.thenApply(ss -> {
            Integer.parseInt(ss);
            sleep(10);
            return ss + "world";
        }).exceptionally(Throwable::getMessage).thenAccept(System.out::println);
        return future;
    }

    private static void obtrudeException() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("==========");
            return "Hello";
        });
        // sleep(1);
        future.obtrudeException(new RuntimeException("i am error."));
        System.out.println(future.get());
    }

    private static void completeExceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // sleep(5);
            System.out.println("==========");
            return "Hello";
        });
        sleep(1);
        future.completeExceptionally(new RuntimeException());
        System.out.println(future.get());
    }

    private static void join() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("==========");
            return "Hello";
        });

        String join = future.join();
        System.out.println(join);
        System.out.println(future.get());
    }

    private static void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            System.out.println("==========");
            return "Hello";
        });
        sleep(1);
        Boolean finished = future.complete("World");
        System.out.println(finished);
        System.out.println(future.get());
    }

    private static void getNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(5);
            return "Hello";
        });

        String ret = future.getNow("World");
        System.out.println(ret);
        System.out.println(future.get());
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
