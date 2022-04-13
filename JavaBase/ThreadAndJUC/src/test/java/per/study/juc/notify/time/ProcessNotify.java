package per.study.juc.notify.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProcessNotify extends Thread implements CallbackNotify
{

    private int note;
    private ReentrantLock lock;
    private Condition cond;

    public ProcessNotify() {
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }

    @Override
    public boolean sendMessage(int note) {
        this.note = note;
        lock.lock();
        cond.signal();
        lock.unlock();
        return true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                /**
                 * true  被唤醒
                 * false 忙等
                 */
                boolean flag = cond.await(10, TimeUnit.SECONDS);
                if (!flag) {
                    continue;
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("rece: " + this.note);
        }
    }
}
