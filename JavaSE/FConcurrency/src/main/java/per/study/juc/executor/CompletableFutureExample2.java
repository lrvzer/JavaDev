package per.study.juc.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample2
{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // supplyAsync();

        // Future<Void> future = runAsync();
        // future.get();

//        Future<Void> future = completed("Hello");
//        System.out.println(future.isDone());

//        System.out.println(">>>" + anyOf().get());
        allOf();

        Thread.currentThread().join();
    }

    private static void create() {
        CompletableFuture<String> future = new CompletableFuture<>();

        String s = null;
        CompletableFuture.supplyAsync(()->s);
    }

    private static void allOf() {
        CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
            System.out.println("1==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1==end");
        }).whenComplete((v, t) -> System.out.println("==over===")), CompletableFuture.supplyAsync(() -> {
            System.out.println("2==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2==end");
            return "Hello";
        }).whenComplete((v, t) -> System.out.println(v + "==over===")));
    }

    private static Future<?> anyOf() {
        return CompletableFuture.anyOf(CompletableFuture.runAsync(() -> {
            System.out.println("1==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1==end");
        }).whenComplete((v, t) -> System.out.println("==over===")), CompletableFuture.supplyAsync(() -> {
            System.out.println("2==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2==end");
            return "Hello";
        }).whenComplete((v, t) -> System.out.println(v + "==over===")));
    }

    private static Future<Void> completed(String data) {
        // return CompletableFuture.supplyAsync(() ->
        // data).thenAccept(System.out::println);
        return CompletableFuture.completedFuture(data).thenAccept(System.out::println);
    }

    private static Future<Void> runAsync() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Obj==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Obj==end");
        }).whenComplete((v, t) -> System.out.println("===over==="));
    }

    private static void supplyAsync() {
        CompletableFuture.supplyAsync(Object::new).thenAccept(obj -> {
            System.out.println("Obj==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Obj==end");
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> "Hello").thenAcceptAsync(s -> {
            System.out.println("String==Start");
            try {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("String==end");
        }), () -> System.out.println("===Finished======"));
    }

}
