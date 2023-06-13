package per.study.juc.utils.condition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionExample3 {

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition PRODUCE_COND = lock.newCondition();
    private static final Condition CONSUME_COND = lock.newCondition();
    private static final LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();
    private static final int MAX_CAPACITY = 100;

    private static void produce() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                PRODUCE_COND.await();
            }
            System.out.println("PRODUCE_COND.getWaitQueueLength>" + lock.getWaitQueueLength(PRODUCE_COND));
            Long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            TIMESTAMP_POOL.add(value);
            CONSUME_COND.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUME_COND.await();
            }
            Long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + value);
            PRODUCE_COND.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void beginProduce(int i) {
        new Thread(() -> {
            for (; ; ) {
                produce();
                sleep(2);
            }
        }, "P-" + i).start();
    }

    private static void beginConsume(int i) {
        new Thread(() -> {
            for (; ; ) {
                consume();
                sleep(3);
            }
        }, "C-" + i).start();
    }

    public static void main(String[] args) {
        IntStream.range(0, 6).boxed().forEach(ConditionExample3::beginProduce);
        IntStream.range(0, 12).boxed().forEach(ConditionExample3::beginConsume);
    }
}
