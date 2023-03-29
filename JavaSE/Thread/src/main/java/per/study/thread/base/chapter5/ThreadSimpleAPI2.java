package per.study.thread.base.chapter5;

import java.util.Optional;

/**
 *     public final void setPriority(int newPriority) {
 *         ThreadGroup g;
 *         checkAccess();
 *         if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
 *             throw new IllegalArgumentException();
 *         }
 *         if((g = getThreadGroup()) != null) {
 *             if (newPriority > g.getMaxPriority()) {
 *                 newPriority = g.getMaxPriority();
 *             }
 *             setPriority0(priority = newPriority);
 *         }
 *     }
 */
public class ThreadSimpleAPI2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 1000; i ++) {
               Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
           }
        });
        t1.setPriority(Thread.MAX_PRIORITY);

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i ++) {
                Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
            }
        });
        t2.setPriority(Thread.NORM_PRIORITY);

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i ++) {
                Optional.of(Thread.currentThread().getName() + "-index" + i).ifPresent(System.out::println);
            }
        });
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }

}
