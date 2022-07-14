package per.study.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PhaserExample1 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(Task::new);

        phaser.register();
        phaser.arriveAndAwaitAdvance();

    }

    static class Task extends Thread {
        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("The worker[" + getName() + "] is working");
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            phaser.arriveAndAwaitAdvance();
        }
    }
}
