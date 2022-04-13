package per.study.juc.notify.time.limit;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Receiver extends Thread implements TimeListen {

    private String message;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    @Override
    public synchronized void receiveMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            while (true) {
                lock.lock();
                boolean await = cond.await(100, TimeUnit.MILLISECONDS);
                if (!await) {
                    System.out.println("Receiver: " + message + ", Timestamp: " + new Date().getTime() + ", " + new Date());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
