package per.study.juc.collections.blocking;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingDequeExample
{
    public static void main(String[] args) throws InterruptedException {
        // testAddFirst();
        // testAdd();
        testTakeFirst();
    }

    private static void testAddFirst() {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
        deque.addFirst("Java");
        deque.addFirst("C");

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
    }

    private static void testAdd() {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
        deque.add("Java");
        deque.add("C");

        System.out.println(deque.remove());
        System.out.println(deque.remove());
    }

    private static void testTakeFirst() throws InterruptedException {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> deque.add("Deque"), 1, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();
        System.out.println(deque.takeFirst());
        long end = System.currentTimeMillis();

        System.out.printf("duration: %d", end - start);
    }
}
