package per.study.juc.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ComplexExample
{

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // final ExecutorService executorService = Executors.newFixedThreadPool(5);

        // List<Runnable> tasks = IntStream.range(0,
        // 5).boxed().map(ComplexExample::toTask).collect(toList());

        // List<Future<?>> futureList = new ArrayList<>();
        // tasks.forEach(r -> futureList.add(executorService.submit(r)));
        // futureList.get(4).get();
        // futureList.get(3).get();
        // futureList.get(2).get();
        // futureList.get(1).get();
        // futureList.get(0).get();

        // final CompletionService<Object> completionService = new
        // ExecutorCompletionService<>(executorService);
        // tasks.forEach(r -> {
        // completionService.submit(Executors.callable(r));
        // });
        // Future<?> future;
        // while ((future = completionService.take()) != null) {
        // System.out.println(future.get());
        // }

        // drap
        // final CompletionService<Object> completionService = new
        // ExecutorCompletionService<>(executorService);
        // tasks.forEach(r -> completionService.submit(Executors.callable(r)));
        // TimeUnit.SECONDS.sleep(5);
        // List<Runnable> runnables = executorService.shutdownNow();
        // System.out.println(runnables);

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        List<Callable<Integer>> tasks = IntStream.range(0, 5).boxed().map(MyTask::new).collect(toList());
        tasks.forEach(completionService::submit);
        TimeUnit.SECONDS.sleep(15);
        executorService.shutdownNow();
        tasks.stream().filter(callable -> !((MyTask)callable).isSuccess()).forEach(System.out::println);
    }

    private static class MyTask implements Callable<Integer>
    {
        private final Integer value;

        private boolean success = false;

        public MyTask(Integer value) {
            this.value = value;
        }

        @Override
        public Integer call() throws Exception {
            System.out.printf("The task [%d] will be executed.\n", value);
            TimeUnit.SECONDS.sleep(value * 5 + 10);
            System.out.printf("The task [%d] execute done.\n", value);
            success = true;
            return value;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    private static Runnable toTask(int i) {
        return () -> {
            try {
                System.out.printf("The task [%d] will be executed.\n", i);
                TimeUnit.SECONDS.sleep(i * 5 + 10);
                System.out.printf("The task [%d] execute done.\n", i);
            }
            catch (InterruptedException e) {
                System.out.printf("The task [%d] be interrupted.\n", i);
            }
        };
    }

}
