package per.study.juc.atomic.chapter2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> atomicInteger = new AtomicStampedReference<>(100, 0);

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);

                boolean success = atomicInteger.compareAndSet(100, 101, atomicInteger.getStamp(), atomicInteger.getStamp() + 1);
                System.out.println("t1: " + success);

                success = atomicInteger.compareAndSet(101, 100, atomicInteger.getStamp(), atomicInteger.getStamp() + 1);
                System.out.println("t1: " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                int stamp = atomicInteger.getStamp();
                System.out.println("Before sleep: stamp = " + stamp);
                TimeUnit.SECONDS.sleep(2);
                boolean success = atomicInteger.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("t2: " + success);
//                atomicInteger.compareAndSet(101, 100, stamp, stamp + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

}
