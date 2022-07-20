package per.study.thread.base.chapter9;

import java.util.stream.Stream;

public class DiffOfWaitAndSleep {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
//        Stream.of("T1", "T2").forEach(name -> new Thread(() -> m1(), name).start());
        Stream.of("T1", "T2").forEach(name -> new Thread(() -> m2(), name).start());
    }

    public static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter.");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName() + " enter.");
                LOCK.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
