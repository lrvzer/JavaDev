package per.study.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserExample4 {
    public static void main(String[] args) throws InterruptedException {
        /*final Phaser phaser = new Phaser(1);*/

       /* System.out.println("0:" + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println("1:" + phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println("2:" + phaser.getPhase());

        phaser.arriveAndAwaitAdvance();
        System.out.println("3:" + phaser.getPhase());*/

        /*System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());*/

        /*System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());*/

        /*phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());
        new Thread(phaser::arriveAndAwaitAdvance).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("======================");
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());
        System.out.println(phaser.getUnarrivedParties());*/

        final Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return false;
            }
        };

        new OnAdvanceTask("Alex", phaser).start();
        new OnAdvanceTask("Jack", phaser).start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());

    }


    static class OnAdvanceTask extends Thread {
        private final Phaser phaser;

        OnAdvanceTask(String name, Phaser phaser) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(getName() + " I am start and the phaser " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println(getName() + " I am end.");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("isTerminated->" + phaser.isTerminated());

            if (getName().equals("Alex")) {
                System.out.println(getName() + " I am start and the phaser " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(getName() + " I am end.");
            }
        }
    }
}
