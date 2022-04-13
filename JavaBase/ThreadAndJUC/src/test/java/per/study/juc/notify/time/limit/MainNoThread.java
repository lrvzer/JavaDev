package per.study.juc.notify.time.limit;

public class MainNoThread {
    public static void main(String[] args) throws InterruptedException {
        ReceiverWithNoThread listen = new ReceiverWithNoThread();
        Sender sender1 = new Sender("Sender1", listen);
        Sender sender2 = new Sender("Sender2", listen);

        sender1.setDaemon(true);
        sender2.setDaemon(true);

        sender1.start();
        sender2.start();
        int i = 0;
        while (i < 10) {
            Thread.sleep(2000L);
            listen.open();
            i++;
        }

    }
}
