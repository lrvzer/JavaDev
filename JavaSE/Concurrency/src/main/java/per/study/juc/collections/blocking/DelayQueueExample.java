package per.study.juc.collections.blocking;

import java.util.Iterator;
import java.util.concurrent.*;

public class DelayQueueExample
{
    public static void main(String[] args) throws InterruptedException {
        // addTest();
        // addTest2();
        // addTest3();
        // addTest4();
        // addTest5();
        // addTest6();
        // pollTest();
        takeTest();
    }

    /**
     * <pre>
     * 1.Add method must add the Delayed element
     * 2.peek method will return null/element (but not remove) be quickly
     * </pre>
     */
    private static void addTest() {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();
        delayQueue.add(DelayElement.of("Delayed1", 1000));
        System.out.println(delayQueue.size());
        long start = System.currentTimeMillis();
        System.out.println(delayQueue.peek().getData());
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
        System.out.println(delayQueue.size());
    }

    private static void addTest2() {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();

        delayQueue.add(DelayElement.of("Delayed1", 1000));
        delayQueue.add(DelayElement.of("Delayed1", 800));
        delayQueue.add(DelayElement.of("Delayed1", 11000));
        delayQueue.add(DelayElement.of("Delayed1", 20000));

        long start = System.currentTimeMillis();

        Iterator<DelayElement<String>> iterator = delayQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
    }

    private static void addTest3() {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();

        delayQueue.add(DelayElement.of("Delayed1", 1000));
        delayQueue.add(DelayElement.of("Delayed1", 800));
        delayQueue.add(DelayElement.of("Delayed1", 11000));
        delayQueue.add(DelayElement.of("Delayed1", 20000));

        long start = System.currentTimeMillis();
        System.out.println(delayQueue.remove().getData());
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
    }

    private static void addTest4() {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();

        delayQueue.add(DelayElement.of("Delayed1", 1000));
        delayQueue.add(DelayElement.of("Delayed1", 800));
        delayQueue.add(DelayElement.of("Delayed1", 11000));
        delayQueue.add(DelayElement.of("Delayed1", 20000));

        long start = System.currentTimeMillis();
        System.out.println(delayQueue.poll().getData());
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
    }

    private static void addTest5() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();

        delayQueue.add(DelayElement.of("Delayed1", 1000));
        delayQueue.add(DelayElement.of("Delayed1", 800));
        delayQueue.add(DelayElement.of("Delayed1", 11000));
        delayQueue.add(DelayElement.of("Delayed1", 20000));

        long start = System.currentTimeMillis();
        System.out.println(delayQueue.take().getData());
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
    }

    private static void addTest6() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();

        delayQueue.add(DelayElement.of("Delayed1", 1000));
        delayQueue.add(DelayElement.of("Delayed1", 80));
        delayQueue.add(DelayElement.of("Delayed1", 11000));
        delayQueue.add(DelayElement.of("Delayed1", 20000));

        TimeUnit.MILLISECONDS.sleep(90);

        long start = System.currentTimeMillis();
        System.out.println(delayQueue.poll().getData());
        // System.out.println(delayQueue.take().getData());
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
    }

    private static void pollTest() {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();
        System.out.println(delayQueue.poll());
    }

    private static void takeTest() throws InterruptedException {
        DelayQueue<DelayElement<String>> delayQueue = new DelayQueue<>();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> delayQueue.add(DelayElement.of(":Test", 1000)), 1, TimeUnit.SECONDS);
        executorService.shutdown();

        long start = System.currentTimeMillis();
        System.out.println(delayQueue.take());
        long end = System.currentTimeMillis();
        System.out.println("delay: " + (end - start));
    }

    static class DelayElement<E> implements Delayed
    {

        private final E e;

        private final long expireTime;

        DelayElement(E e, long expireTime) {
            this.e = e;
            this.expireTime = expireTime + System.currentTimeMillis();
        }

        static <T> DelayElement<T> of(T e, long delay) {
            return new DelayElement<T>(e, delay);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expireTime - System.currentTimeMillis();

            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayedObject) {
            DelayElement another = (DelayElement) delayedObject;
            if (this.expireTime < another.getExpireTime()) {
                return -1;
            }
            else if (this.expireTime > another.getExpireTime())
                return 1;
            return 0;
        }

        public E getData() {
            return e;
        }

        public long getExpireTime() {
            return expireTime;
        }
    }
}
