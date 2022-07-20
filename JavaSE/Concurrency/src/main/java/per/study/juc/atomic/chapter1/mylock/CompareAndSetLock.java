package per.study.juc.atomic.chapter1.mylock;

import java.util.concurrent.atomic.AtomicInteger;

// 利用CAS构造一个TryLock自定义显式锁
public class CompareAndSetLock {

    // Monitor
    private final AtomicInteger value = new AtomicInteger(0);

    // 被锁住的线程，用于对该线程解锁
    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean success = value.compareAndSet(0, 1);
        if (!success)
            throw new GetLockException("Get the Lock failed");
        else
            lockedThread = Thread.currentThread();
    }

    public void unlock() {
        if (0 == value.get()) {
            return;
        }

        if (lockedThread == Thread.currentThread())
            value.compareAndSet(1, 0);
    }
}
