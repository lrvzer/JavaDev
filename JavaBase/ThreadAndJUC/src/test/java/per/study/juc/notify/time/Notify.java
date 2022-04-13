package per.study.juc.notify.time;

public class Notify extends Thread {

    private boolean closed = false;
    private final CallbackNotify callbackNotify;

    public Notify(CallbackNotify callbackNotify) {
        this.callbackNotify = callbackNotify;
    }

    @Override
    public void run() {
        int i = 0;
        while (!closed && i < 10) {
            System.out.println("send: " + i);
            boolean b = callbackNotify.sendMessage(i);
            if (b) {
                i++;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        closed = true;
    }

}
