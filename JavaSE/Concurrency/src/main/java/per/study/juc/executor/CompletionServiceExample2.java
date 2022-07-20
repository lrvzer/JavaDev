package per.study.juc.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletionServiceExample2
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // test();
        test2();
    }

    private static void test() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> callableList = Arrays.asList(() -> {
            sleep(10);
            System.out.println("the 10 finished.");
            return 10;
        }, () -> {
            sleep(20);
            System.out.println("the 20 finished.");
            return 20;
        });

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        List<Future<Integer>> futures = new ArrayList<>();
        callableList.stream().forEach(callable -> {
            futures.add(completionService.submit(callable));
        });

        Future<Integer> f;
        // while ((f = completionService.take()) != null) {
        // System.out.println(f.get());
        // }
        Future<Integer> poll = completionService.poll();
        System.out.println(poll.get());
    }

    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test2() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(executorService);
        final Event event = new Event(1);
        completionService.submit(new MyTask(event), event);
        System.out.println(completionService.take().get().getResult());
    }

    private static class MyTask implements Runnable
    {
        private final Event event;

        public MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(10);
            event.setResult("successfully.");
        }
    }

    private static class Event
    {
        final private int eventId;
        private String result;

        public Event(int eventId) {
            this.eventId = eventId;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

}
