package per.study.juc.utils.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


/**
 * CountDownLatch模拟CyclicBarrier
 * <p>CountDownLatch vs CyclicBarrier</p>
 * <p>
 *     1.CountDownLatch不能reset，而CyclicBarrier可以循环使用
 * </p>
 * <p>
 *     2.工作线程之间互不关心；工作线程必须等到等到同一个共同的点才去执行某个动作
 * </p>
 */
public class CyclicBarrierExample3 {
    static class MyCountDownLatch extends CountDownLatch {

        private final Runnable runnable;

        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0) {
                this.runnable.run();
            }
        }
    }

    public static void main(String[] args) {
        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("All of work is done.");
            }
        });

        new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myCountDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished work");
        }).start();


        new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myCountDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished work");
        }).start();
    }

}
