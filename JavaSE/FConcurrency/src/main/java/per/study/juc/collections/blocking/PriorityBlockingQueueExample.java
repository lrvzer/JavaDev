package per.study.juc.collections.blocking;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample
{

    public static void main(String[] args) throws InterruptedException {
//        add();
        get();
    }

    /**
     * add() offer() put()
     */
    private static void add() {
        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue(5);
        System.out.println("hello1: " + queue.add("hello1"));
        System.out.println("hello2: " + queue.add("hello2"));
        System.out.println("hello3: " + queue.add("hello3"));
        System.out.println("hello4: " + queue.add("hello4"));
        System.out.println("hello5: " + queue.add("hello5"));
        System.out.println("hello6: " + queue.add("hello6"));
        System.out.println(queue.size());
    }

    /**
     * element() peek()首元素，拿而不删
     * poll() remove()  拿且删
     */
    private static void get() throws InterruptedException {
        PriorityBlockingQueue<String> queue = new PriorityBlockingQueue(5);
        System.out.println("hello1: " + queue.add("hello1"));
        System.out.println("hello2: " + queue.add("hello2"));
        System.out.println("hello3: " + queue.add("hello3"));

        System.out.println(queue.element());
        System.out.println(queue.element());
        System.out.println(queue.element());
        ///////////////////////////////////
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        ///////////////////////////////////
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        ///////////////////////////////////
//        System.out.println(queue.remove());
        ///////////////////////////////////

        queue.take();
    }



}
