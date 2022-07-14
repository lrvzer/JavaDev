package per.study.juc.utils.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ForkJoinRecursiveAction {

    private static final int MAX_THRESHOLD = 3;
    private static final AtomicInteger SUM = new AtomicInteger(0);

    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;

        public CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int mid = (start + end) >> 1;
                CalculateRecursiveAction leftAction = new CalculateRecursiveAction(start, mid);
                CalculateRecursiveAction rightAction = new CalculateRecursiveAction(mid + 1, end);

                leftAction.fork();
                rightAction.fork();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ForkJoinPool forkJoin = new ForkJoinPool();
        forkJoin.submit(new CalculateRecursiveAction(0, 10));
        forkJoin.awaitTermination(10, TimeUnit.SECONDS);
        Optional.of(SUM).ifPresent(System.out::println);
    }

}
