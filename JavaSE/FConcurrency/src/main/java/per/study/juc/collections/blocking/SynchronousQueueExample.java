package per.study.juc.collections.blocking;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueExample
{
    public static void main(String[] args) throws InterruptedException {
        add();
    }

    private static void add() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue();

        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        TimeUnit.MILLISECONDS.sleep(5);
        queue.offer("SynchronousQueue");
    }

}