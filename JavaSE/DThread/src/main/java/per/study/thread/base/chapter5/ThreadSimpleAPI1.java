package per.study.thread.base.chapter5;

import java.util.Optional;

public class ThreadSimpleAPI1 {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            Optional.of("per.study.juc.atomic.jni.Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t.start();

        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);

    }
}
