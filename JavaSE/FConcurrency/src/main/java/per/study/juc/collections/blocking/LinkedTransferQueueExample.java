package per.study.juc.collections.blocking;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LinkedTransferQueueExample

{
    private static final LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) throws InterruptedException {
        // testTryTransfer();
        // testTransfer();
        testTransfer2();
    }

    private static void testTryTransfer() {
        System.out.println(queue.tryTransfer("Transfer"));
        System.out.println(queue.size());
    }

    private static void testTransfer() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                String element = queue.take();
                System.out.println(element);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();
        queue.transfer("Transfer");
        long end = System.currentTimeMillis();
        System.out.println("duration: " + (end - start));

        System.out.println(queue.size());
    }

    private static void testTransfer2() throws InterruptedException {
        List<Callable<String>> collect = IntStream.range(0, 5).boxed().map(i -> (Callable<String>) () -> {
            try {
                return queue.take();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        ExecutorService executorService = Executors.newCachedThreadPool();
        collect.stream().forEach(executorService::submit);
        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println(queue.getWaitingConsumerCount());
        System.out.println(queue.hasWaitingConsumer());

        IntStream.range(0, 5).boxed().map(String::valueOf).forEach(queue::add);
        TimeUnit.MILLISECONDS.sleep(5);

        System.out.println(queue.getWaitingConsumerCount());
        System.out.println(queue.hasWaitingConsumer());
    }
}
