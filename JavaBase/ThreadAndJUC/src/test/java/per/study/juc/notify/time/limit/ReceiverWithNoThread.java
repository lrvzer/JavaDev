package per.study.juc.notify.time.limit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReceiverWithNoThread implements TimeListen{

    private String message;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    @Override
    public void receiveMessage(String message) {
        this.message = message;
    }

    private void handleMessage(String message) {
        if (message.contains("Sender")) {
            try {
                lock.lock();
                boolean flag = lock.newCondition().await(3, TimeUnit.SECONDS);
                if (!flag) {
                    handleMessageSenderOne(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.lock();
            }
        }
    }

    private synchronized void handleMessageSenderOne(String message) {
        System.out.println(message);
    }

    public void open() {
        handleMessage(message);
    }

}
