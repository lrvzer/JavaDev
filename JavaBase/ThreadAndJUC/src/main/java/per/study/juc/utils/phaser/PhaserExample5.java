package per.study.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample5 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        /*final Phaser phaser = new Phaser(3);

        new Thread(phaser::arrive).start();

        TimeUnit.SECONDS.sleep(4);*/

        final Phaser phaser = new Phaser(5);
        for (int i =0; i < 4; i++) {
            new ArriveTask(i, phaser).start();
        }

        phaser.arriveAndAwaitAdvance();
        System.out.println("The plan 1 work finished.");

    }

    private static class ArriveTask extends Thread {

        private final Phaser phaser;

        private ArriveTask(int no, Phaser phaser) {
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " start working.");
            PhaserExample5.sleep();
            System.out.println(getName() + " the phaser one is running.");
            phaser.arrive();

            PhaserExample5.sleep();
            System.out.println(getName() + " keep to do other things.");
        }
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
