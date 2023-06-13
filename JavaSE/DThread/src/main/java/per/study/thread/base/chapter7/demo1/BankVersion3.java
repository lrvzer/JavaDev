package per.study.thread.base.chapter7.demo1;

public class BankVersion3 {
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindow = new TicketWindowRunnable();
        Thread t1 = new Thread(ticketWindow, "一号柜台");
        Thread t2 = new Thread(ticketWindow, "二号柜台");
        Thread t3 = new Thread(ticketWindow, "三号柜台");
        t1.start();
        t2.start();
        t3.start();
    }
}
