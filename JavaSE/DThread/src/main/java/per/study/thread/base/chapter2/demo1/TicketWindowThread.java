package per.study.thread.base.chapter2.demo1;

public class TicketWindowThread extends Thread{

    private static final int MAX = 50;
    private static int index = 1; // 线程安全问题
    private final String name;

    public TicketWindowThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台：" + name + "，当前号码是" + index++);
        }
    }
}
