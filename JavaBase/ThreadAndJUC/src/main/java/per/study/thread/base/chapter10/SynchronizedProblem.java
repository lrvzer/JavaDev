package per.study.thread.base.chapter10;

public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        new Thread(SynchronizedProblem::run, "T1").start();

        Thread.sleep(1000);

        Thread t2 = new Thread(SynchronizedProblem::run, "T2");

        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }

    public synchronized static void run() {
        System.out.println(Thread.currentThread().getName());
        while (true) {

        }
    }
}
