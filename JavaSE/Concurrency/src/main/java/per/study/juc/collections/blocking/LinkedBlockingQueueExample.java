package per.study.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample
{
    public static void main(String[] args) {
        // insertData();
        removeData();
    }

    private static void insertData() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(2);
        queue.add("hello1");
        queue.add("hello2");
        // queue.add("hello3");
        queue.clear();

        System.out.println(queue.offer("hello1"));
        System.out.println(queue.offer("hello2"));
        System.out.println(queue.offer("hello3"));

        queue.clear();

        try {
            queue.put("hello1");
            queue.put("hello2");
            queue.put("hello3");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void removeData() {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(2);
        queue.offer("hello1");
        queue.offer("hello2");
        queue.offer("hello3");

//        System.out.println(queue.element());
//        System.out.println(queue.element());
//
//        System.out.println(queue.peek());
//        System.out.println(queue.peek());

//        System.out.println(queue.poll());
//        System.out.println(queue.poll());

//        System.out.println(queue.remove());
//        System.out.println(queue.remove());

        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
