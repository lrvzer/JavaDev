package per.study.thread.base.chapter10;

import java.util.Collection;

public interface Lock {

    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    /**
     * 可中断
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 可中断，可超时
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    void lock(long mills) throws InterruptedException, TimeOutException;

    /**
     * 释放锁
     */
    void unlock();

    /**
     * 获取当前有哪些线程被阻塞
     * @return
     */
    Collection<Thread> getBlockThread();

    int getBlockSize();

}
