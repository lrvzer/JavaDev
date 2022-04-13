package per.study.juc.design.chapter6;

/**
 * 读写锁
 */
public class ReadWriteLock {

    /*正在读操作的数量*/
    private int readingReaders = 0;

    /*等待读操作的数量*/
    private int waitingReaders = 0;

    /*正在写操作的数量*/
    private int writingWriters = 0;

    /*等待写操作的数量*/
    private int waitingWriters = 0;

    private boolean preferWriter;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders ++;
        try {
            while (this.writingWriters > 0 || (preferWriter && this.waitingWriters > 0)) {
                this.wait();
            }
            /*等待写操作结束，读操作+1*/
            this.readingReaders ++;
        } finally {
            this.waitingReaders --;
        }
    }

    public synchronized void readUnlock() {
        this.readingReaders --;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters ++;
        try {
            while (this.writingWriters > 0) {
                this.wait();
            }
            this.writingWriters ++;
        } finally {
            this.waitingWriters --;
        }
    }

    public synchronized void writeUnlock() {
        this.writingWriters --;
        this.notifyAll();
    }

}
