package per.study.juc.collections.custom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class LockFreeQueue<E>
{
    private AtomicReference<Node<E>> head, tail;
    private AtomicInteger size = new AtomicInteger(0);

    public LockFreeQueue() {
        Node<E> node = new Node<>(null);
        this.head = new AtomicReference<>(node);
        this.tail = new AtomicReference<>(node);
    }

    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException("value is null");
        Node<E> newNode = new Node<>(e);
        Node<E> previous = tail.getAndSet(newNode);
        previous.next = newNode;
        size.incrementAndGet();
    }

    public E removeFirst() {
        Node<E> headNode;
        Node<E> valueNode;
        do {
            headNode = head.get();
            valueNode = headNode.next;
        }
        while (valueNode != null && !head.compareAndSet(headNode, valueNode));
        E value = (valueNode != null) ? valueNode.element : null;
        if (valueNode != null) {
            valueNode.element = null;
        }
        // size.decrementAndGet();
        return value;
    }

    public boolean isEmpty() {
        return head.get().next == null;
    }

    private static class Node<E>
    {
        E element;
        volatile Node<E> next;

        public Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return element == null ? "" : element.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final LockFreeQueue<Long> queue = new LockFreeQueue<>();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            int counter = 0;
            while (counter < 10) {
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter++;
                queue.addLast(System.currentTimeMillis());
            }
        }).forEach(executorService::submit);

        IntStream.range(0, 5).boxed().map(l -> (Runnable) () -> {
            int counter = 10;
            while (counter > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter--;
                System.out.println(queue.removeFirst());
            }
        }).forEach(executorService::submit);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

    }

}
