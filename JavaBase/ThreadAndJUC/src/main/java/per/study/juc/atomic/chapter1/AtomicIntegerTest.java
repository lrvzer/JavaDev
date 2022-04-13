package per.study.juc.atomic.chapter1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger与volatile对比
 */
public class AtomicIntegerTest {

    /**
     * volatile
     * 1. 可见性
     * 2. 顺序一致性
     * 3. no atomic
     *    value += 1
     *        (1) get value from main memory to local memory
     *        (2) add 1 => value
     *        (3) assign the value to value
     *        (4) flush to main memory
     *
     */
//    private volatile int value;
    private static final Set<Integer> SET = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger value = new AtomicInteger();

        Thread t1 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int v = value.getAndIncrement();
                SET.add(v);
                System.out.println(Thread.currentThread().getName() + ": " + v);
                x++;
            }
        });

        Thread t2 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int v = value.getAndIncrement();
                SET.add(v);
                System.out.println(Thread.currentThread().getName() + ": " + v);
                x++;
            }
        });

        Thread t3 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                int v = value.getAndIncrement();
                SET.add(v);
                System.out.println(Thread.currentThread().getName() + ": " + v);
                x++;
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(SET.size());
    }

}
