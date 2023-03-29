package per.study.thread.base.chapter7.demo1;

public class TicketWindowRunnable implements Runnable{

    private static final int MAX = 500;
    private int index = 1;
    private static final Object MONITOR = new Object();

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
