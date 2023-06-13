package per.study.thread.base.chapter7;

public class SynchronizedStatic {

    static {
        synchronized (SynchronizedStatic.class) {
            try {
                System.out.println("static " + Thread.currentThread().getName());
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void m1() {
        try {
            System.out.println("m1 " + Thread.currentThread().getName());
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void m2() {
        try {
            System.out.println("m2 " + Thread.currentThread().getName());
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        try {
            System.out.println("m3 " + Thread.currentThread().getName());
            Thread.sleep(10_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
