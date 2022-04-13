package per.study.juc.thread.chapter7;

public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock lock = new ThisLock();
        new Thread(()->{
            lock.m1();
        }, "T1").start();

        new Thread(()->{
            lock.m2();
        }, "T2").start();
    }
}

class ThisLock {
    private final Object LOCK = new Object();

    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}