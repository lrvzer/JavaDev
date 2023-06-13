package per.study.juc.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample3
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        future.thenRun(()-> System.out.println("DONE"));
        future.thenRunAsync(()-> System.out.println("DONE"));

//        future.thenAcceptAsync(System.out::println);

//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello").toCompletableFuture();


//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync((Supplier<String>) () -> {
//            throw new RuntimeException("no get the data");
//        }).handle((s, t) -> {
//            Optional.of(t).ifPresent(e -> System.out.println("Error"));
//            return (s == null) ? 0 : s.length();
//        });


//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
//        future.thenApply(String::length);

//        future.thenApplyAsync(s -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            }
//            catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("DONE");
//            return s.length();
//        });

//        future.whenComplete((v, t) -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            }
//            catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("DONE");
//        });

//        future.whenCompleteAsync((v, t) -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            }
//            catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("==OVER==");
//        });
        
        System.out.println(future.get());
        Thread.currentThread().join();
    }
}
