package per.study.juc.list;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicIntegerDetails {

    @Test
    public void testCreate() {
        AtomicInteger value = new AtomicInteger();
        System.out.println(value.get());

        value = new AtomicInteger(10);
        System.out.println(value.get());

        value.set(12);
        System.out.println(value.get());

        value.lazySet(100);
        System.out.println(value.get());
    }

    @Test
    public void testGetAndAdd() {
/*
        AtomicInteger value = new AtomicInteger(10);
        int result = value.getAndAdd(10);
        System.out.println(result);
        System.out.println(value);
*/

/*

        AtomicInteger value2 = new AtomicInteger();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int v = value2.addAndGet(1);
                System.out.println(v);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                int v = value2.addAndGet(1);
                System.out.println(v);
            }
        }).start();
*/

        AtomicInteger value = new AtomicInteger(10);
        boolean b = value.compareAndSet(10, 20);
        System.out.println(b);
        System.out.println(value);

    }

}
