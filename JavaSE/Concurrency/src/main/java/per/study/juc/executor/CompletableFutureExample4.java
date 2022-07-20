package per.study.juc.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample4
{
    public static void main(String[] args) throws InterruptedException {
        // thenAcceptBoth();
        // acceptEither();
        // runAfterBoth();
        // combine();
        compose();
        Thread.currentThread().join();
    }

    private static void compose() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the compose1");
            sleep(5);
            System.out.println("end the compose1");
            return "combine-1";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("start the compose2");
            sleep(5);
            System.out.println("end the compose2");
            return s.length();
        })).thenAccept(System.out::println);
    }

    private static void combine() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the combine1");
            sleep(5);
            System.out.println("end the combine1");
            return "combine-1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the combine2");
            sleep(5);
            System.out.println("end the combine2");
            return 100;
        }), (s, i) -> s.length() > i).whenComplete((v, t) -> System.out.println(v));
    }

    private static void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync1");
            sleep(5);
            System.out.println("end the suppleAsync1");
            return "acceptEither-1";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync2");
            sleep(5);
            System.out.println("end the suppleAsync2");
            return 100;
        }), () -> System.out.println("DONE"));
    }

    private static void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync1");
            sleep(5);
            System.out.println("end the suppleAsync1");
            return "acceptEither-1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync2");
            sleep(5);
            System.out.println("end the suppleAsync2");
            return "acceptEither-2";
        }), System.out::println);
    }

    private static void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync");
            sleep(5);
            System.out.println("end the suppleAsync");
            return "hello, world!";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync");
            sleep(5);
            System.out.println("end the suppleAsync");
            return 10;
        }), (s, i) -> System.out.println(s + "---" + i));

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
