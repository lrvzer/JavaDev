package per.study.juc.notify.time.limit;

public class Main {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Sender sender1 = new Sender("sender1", receiver);
        Sender sender2 = new Sender("sender2", receiver);

        receiver.start();
        sender1.start();
        sender2.start();
    }
}
