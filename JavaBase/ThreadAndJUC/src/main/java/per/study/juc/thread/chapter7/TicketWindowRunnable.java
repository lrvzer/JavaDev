package per.study.juc.thread.chapter7;

public class TicketWindowRunnable implements Runnable{

    private final int MAX = 500;
    private int index = 1;

    private final Object MONITOR = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (MONITOR) {
                if (index > MAX) {
                    break;
                }

                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "，当前号码是" + index++);
            }
        }
    }
}
