package per.study.thread.base.chapter2.demo2;

public class TicketWindowRunnable implements Runnable{

    private final int MAX = 50;
    private int index = 1;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread().getName() + "，当前号码是" + index++);
        }
    }
}
