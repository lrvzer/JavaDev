package per.study.thread.base.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1 is running");
                Thread.sleep(10_000);
                System.out.println("t1 is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        try {
            t1.join(100, 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Optional.of("All of tasks finish done").ifPresent(System.out::println);
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));

//        Thread.currentThread().join();
    }
}
