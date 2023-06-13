package per.study.thread.base.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock {

    // The locked is true indicated the lock has been got.
    // The locked is false indicated the lock is free (other thread can get this.)
    private boolean locked;

    private final Collection<Thread> blockThreadCollection = new ArrayList<>();

    private Thread currentThread; // 当前线程

    public BooleanLock() {
        locked = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (locked) {
            final Thread tempThread = Thread.currentThread();
            try {
                if (!blockThreadCollection.contains(tempThread)) {
                    blockThreadCollection.add(tempThread);
                }
                this.wait();
            } catch (InterruptedException e) {
                // 避免内存泄漏
                blockThreadCollection.remove(tempThread);
                e.printStackTrace();
            }
        }
        blockThreadCollection.remove(Thread.currentThread());
        this.locked = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws TimeOutException, InterruptedException {
        if (mills <= 0)
            lock();

        else {
            long hasRemain = mills;
            long endTime = System.currentTimeMillis() + mills;
            while (locked) {
                if (hasRemain <= 0)
                    throw new TimeOutException("Time out");
                Thread tempThread = Thread.currentThread();
                try {
                    if (!blockThreadCollection.contains(tempThread))
                        blockThreadCollection.add(tempThread);
                    this.wait(mills);
                    hasRemain = endTime - System.currentTimeMillis();
                } catch (InterruptedException e) {
                    blockThreadCollection.remove(tempThread);
                    e.printStackTrace();
                }
            }
            blockThreadCollection.remove(Thread.currentThread());
            this.locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public synchronized void unlock() {
        if (currentThread == Thread.currentThread()) { // 获取到锁才有子和解锁
            this.locked = false;
            System.out.println(Thread.currentThread().getName() + " release the lock monitor.");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockThread() {
        return Collections.unmodifiableCollection(blockThreadCollection);
    }

    @Override
    public int getBlockSize() {
        return blockThreadCollection.size();
    }
}
