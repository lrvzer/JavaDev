package per.study.juc.design.chapter6;

import java.util.Arrays;

public class SharedData {

    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return this.doRead();
        } finally {
            lock.readUnlock();
        }
    }

    /**
     * 写操作
     * @param c
     * @throws InterruptedException
     */
    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        Arrays.fill(buffer, c);
        slowly(10);
    }

    private char[] doRead() {
        char[] newBuf = Arrays.copyOf(buffer, buffer.length);
        slowly(500);
        return newBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            //TODO
        }
    }

}
