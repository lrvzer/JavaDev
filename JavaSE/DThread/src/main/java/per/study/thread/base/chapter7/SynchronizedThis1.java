package per.study.thread.base.chapter7;

public class SynchronizedThis1 {
    public static void main(String[] args) {
        ThisMonitorLock lock = new ThisMonitorLock();
        new Thread(() -> {
            lock.m1();
        }, "T1").start();

        new Thread(() -> {
            lock.m2();
        }, "T2").start();

        new Thread(() -> {
            lock.m3();
        }, "T3").start();
    }
}

class ThisMonitorLock {

    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void m3() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}