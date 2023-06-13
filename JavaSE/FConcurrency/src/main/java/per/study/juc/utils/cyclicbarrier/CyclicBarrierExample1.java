package per.study.juc.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample1 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
//                System.out.println("all of thread finished. ");
            }
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("T1 finished. ");
                cyclicBarrier.await();
                System.out.println("T1 The other thread finished too. ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("T2 finished. ");
                cyclicBarrier.await();
                System.out.println("T2 The other thread finished too. ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }).start();


        cyclicBarrier.await();
        System.out.println("all of thread finished. ");
//        while (true) {
//            System.out.println(cyclicBarrier.getNumberWaiting());
//            System.out.println(cyclicBarrier.getParties());
//            System.out.println(cyclicBarrier.isBroken());
//            TimeUnit.MILLISECONDS.sleep(1000);
//        }
    }
}
