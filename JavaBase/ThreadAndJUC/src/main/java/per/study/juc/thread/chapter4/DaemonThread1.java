package per.study.juc.thread.chapter4;

public class DaemonThread1 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(50000);
                    System.out.println(Thread.currentThread().getName() + " dead");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; // new
        t.setDaemon(true);
        // runnable ->running| ->dead| ->blocked
        t.start();
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
/**
 * A<-------------------->B
 *  ->daemonThread(health check)
 */