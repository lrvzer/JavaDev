package per.study.juc.utils.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 读写锁
public class ReadWriteLockExample {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    // 被保护数据
    private static final List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(ReadWriteLockExample::write, "Write-1").start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(ReadWriteLockExample::write, "Write-2").start();
        TimeUnit.SECONDS.sleep(1);

        new Thread(ReadWriteLockExample::read, "Read-1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(ReadWriteLockExample::read, "Read-2").start();
        TimeUnit.SECONDS.sleep(1);
    }

    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + "==================");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }

}
