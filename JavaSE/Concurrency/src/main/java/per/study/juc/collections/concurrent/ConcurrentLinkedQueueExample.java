package per.study.juc.collections.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExample
{
    public static void main(String[] args) {

        final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 100000; i++) {
            queue.offer(System.nanoTime());
        }

        long startTime = System.currentTimeMillis();
        // while (queue.size() > 0) {
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println("offer done.");
    }

    private static void handleText(String s) {
        if (null != s && !"".equals(s)) {

        }

        if (null != s && s.length() > 0) {

        }

        if (null != s && !s.isEmpty()) {

        }
    }
}
