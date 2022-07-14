package per.study.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Exchanger用于两个线程之间交换数据
 */
public class ExchangerExample1 {

    /**
     * V r = exchange(V v) <p>
     * &nbsp;&nbsp;v: indicate the object you wanted transfer.<p>
     * &nbsp;&nbsp;r: indicate the other thread(pair) return object.<p>
     *
     * <pre>
     *     Note:
     *      1. If the pair thread not reached exchange point,
     *         the thread will WAITING.
     *      2. Use the {@link Exchanger} most be paired.
     * </pre>
     */
    public static void main(String[] args) {
        final Exchanger exchanger = new Exchanger<String>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
//                String result = (String) exchanger.exchange("I am come from T-A", 10, TimeUnit.SECONDS);
                String result = (String) exchanger.exchange("I am come from T-A");
                System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " end.");
        }, "T-A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                TimeUnit.SECONDS.sleep(20);
                String result = (String) exchanger.exchange("I am come from T-B");
                System.out.println(Thread.currentThread().getName() + " Get value [" + result + "]");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " end.");
        }, "T-B").start();
    }

}
