package per.study.juc.collections.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueExample
{

    /**
     * ArrayBlockingQueue 1.FIFO (first-in-first-out) 2.Once created, the capacity
     * cannot be changed
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        // addMethod();
        // offerMethod();
        // putMethod();
        // pollMethod();
        // peekMethod();
        // elementMethod();
        // removeMethod();
        drainToMethod();
    }

    private static void addMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(5);
        System.out.println("hello1: " + queue.add("hello1"));
        System.out.println("hello2: " + queue.add("hello2"));
        System.out.println("hello3: " + queue.add("hello3"));
        System.out.println("hello4: " + queue.add("hello4"));
        System.out.println("hello5: " + queue.add("hello5"));
        // System.out.println("hello6: " + queue.add("hello6"));
        System.out.println(queue.size());
    }

    private static void offerMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        System.out.println("hello1: " + queue.offer("hello1"));
        System.out.println("hello2: " + queue.offer("hello2"));
        System.out.println("hello3: " + queue.offer("hello3"));
        System.out.println("hello4: " + queue.offer("hello4"));
        System.out.println("hello5: " + queue.offer("hello5"));
        System.out.println(queue.size());
        System.out.println("hello6: " + queue.offer("hello6"));
        System.out.println(queue.size());
    }

    private static void putMethod() throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        ScheduledExecutorService executorService = (ScheduledExecutorService) Executors
                .newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            String take;
            try {
                take = queue.take();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(take);
        }, 1, TimeUnit.SECONDS);

        executorService.shutdown();

        queue.put("hello1");
        queue.put("hello2");
        queue.put("hello3");
        queue.put("hello4");
        queue.put("hello5");
        queue.put("hello6");
    }

    private static void pollMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("Hello1");
        queue.add("Hello2");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        System.out.println(queue.size());
    }

    private static void peekMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("Hello1");
        queue.add("Hello2");
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.peek());

        System.out.println(queue.size());
    }

    private static void elementMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("Hello1");
        queue.add("Hello2");
        System.out.println(queue.element());
        System.out.println(queue.element());
        System.out.println(queue.element());

        System.out.println(queue.size());
    }

    private static void removeMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("Hello1");
        queue.add("Hello2");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        System.out.println(queue.size());
    }

    private static void drainToMethod() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        queue.add("Hello1");
        queue.add("Hello2");

        System.out.println(queue.size());
        List<String> list = new ArrayList<>();
        queue.drainTo(list);
        System.out.println(queue.size());

        System.out.println(list);
    }

}
