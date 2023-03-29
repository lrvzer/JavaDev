package per.study.thread.base.chapter2.demo1;

public class BankVersion1 {
    public static void main(String[] args) {
        TicketWindowThread t1 = new TicketWindowThread("一号柜台");
        t1.start();

        TicketWindowThread t2 = new TicketWindowThread("二号柜台");
        t2.start();

        TicketWindowThread t3 = new TicketWindowThread("三号柜台");
        t3.start();
    }
}
