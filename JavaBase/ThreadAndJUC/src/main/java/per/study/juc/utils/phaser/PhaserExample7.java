package per.study.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PhaserExample7 {

    /**
     * awaitAdvanceInterruptibly
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
       /*
        // 并未被中断
        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(phaser::arriveAndAwaitAdvance);
        thread.start();
        System.out.println("================");

        TimeUnit.SECONDS.sleep(10);
        thread.interrupt();
        System.out.println("====thread.interrupt====");*/

        /*final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(phaser.getPhase());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        System.out.println("================");
        TimeUnit.SECONDS.sleep(10);
        thread.interrupt();
        System.out.println("====thread.interrupt====");*/

        final Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(0, 10, TimeUnit.SECONDS);
                System.out.println("********");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

    }

}
