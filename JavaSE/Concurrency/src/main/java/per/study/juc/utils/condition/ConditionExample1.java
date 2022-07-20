package per.study.juc.utils.condition;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample1 {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition cond = lock.newCondition();

    private static int data = 0;

    private static volatile boolean noUse = true;

    private static void buildData() {
        try {
            lock.lock(); // synchronized #monitor enter
            while (noUse) {
                cond.await(); // monitor.wait()
            }
            data++;
            Optional.of("P:" + data).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
            noUse = true;
            cond.signal(); // monitor.notify()
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock(); // synchronized end #monitor end
        }
    }

    private static void useData() {
        try {
            lock.lock();
            while (!noUse) {
                cond.await();
            }
//            TimeUnit.SECONDS.sleep(1);
            Optional.of("C:" + data).ifPresent(System.out::println);
            noUse = false;
            cond.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; )
                buildData();
        }).start();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (; ; )
                    useData();
            }).start();
        }
    }
}
