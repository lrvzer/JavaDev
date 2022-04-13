package per.study.juc.notify.time.limit;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sender extends Thread {

    private final TimeListen timeListen;
    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    public Sender(String name, TimeListen timeListen) {
        super(name);
        this.timeListen = timeListen;
    }

    @Override
    public void run() {
        try {
            int i = 1;
            while (true) {
                lock.lock();
                boolean await = cond.await(1, TimeUnit.SECONDS);
                if (!await) {
                    timeListen.receiveMessage("[" + Thread.currentThread().getName() + ": " + (i++) + ", Timestamp: " + new Date().getTime() + ", " + new Date() + "]");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.lock();
        }
    }
}
