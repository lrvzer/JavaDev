package per.study.thread.base.chapter7;

public class SynchronizedRunnable implements Runnable{

    // read only
    private final static int MAX = 500;
    private int index = 1;

    @Override
    public void run() {
        while (true) {
            if (ticket()) break;
        }
    }

    private synchronized boolean ticket() {
        if (index > MAX) return true;
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "，当前号码是" + index++);
        return false;
    }
}
