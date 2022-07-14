package per.study.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 5; i++) {
            new Athletes(i, phaser).start();
        }

        new InjureAthletes(5, phaser).start();
    }

    static class InjureAthletes extends Thread {
        private final int no;
        private final Phaser phaser;


        InjureAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(phaser, no, ": start running.", ": end running.");
                sport(phaser, no, ": start bicycle.", ": end bicycle.");

                System.out.println(no + ": oh shit, i injured. i will be exited.");
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Athletes extends Thread {
        private final int no;
        private final Phaser phaser;


        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(phaser, no, ": start running.", ": end running.");
                sport(phaser, no, ": start bicycle.", ": end bicycle.");
                sport(phaser, no, ": start long jump.", ": end long jump.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private static void sport(Phaser phaser, int no, String x, String x1) throws InterruptedException {
        System.out.println(no + x);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println(no + x1);
        phaser.arriveAndAwaitAdvance();
    }

}
