package per.study.thread.base.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock{

    // The initValue is true indicated the lock has been got.
    // The initValue is false indicated the lock is free(other thread can get this.)
    private boolean initValue;

    private Collection<Thread> blockThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0)
            lock();

        long hasRemain = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemain <= 0)
                throw new TimeOutException("Time out");
            blockThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemain = endTime - System.currentTimeMillis();
        }
//        blockThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (currentThread == Thread.currentThread()) {
            this.initValue = false;
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
