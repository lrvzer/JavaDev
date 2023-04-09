package per.study.thread.base.chapter10;

/**
 * synchronized关键字缺陷
 * 1.阻塞时长无法控制
 * 2.不可被中断
 */
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

    public static synchronized void run() {
        System.out.println(Thread.currentThread().getName());
        while (true) {
            // TODO
        }
    }
}
