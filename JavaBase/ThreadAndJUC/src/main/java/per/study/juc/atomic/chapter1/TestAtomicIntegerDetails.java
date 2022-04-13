package per.study.juc.atomic.chapter1;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicIntegerDetails {

    public static void main(String[] args) {

        /**
         * create
         */
        AtomicInteger value = new AtomicInteger();
        System.out.println(value.get());

        value = new AtomicInteger(10);
        System.out.println(value.get());

        /**
         * set
         */
        value.set(12);
        System.out.println(value.get());

//        value.lazySet(100);
//        System.out.println(value.get());

        System.out.println("====================");

        AtomicInteger getAndSet = new AtomicInteger(0);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int v = getAndSet.addAndGet(1);
                System.out.println(v);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int v = getAndSet.addAndGet(1);
                System.out.println(v);
            }
        }).start();

    }

}
