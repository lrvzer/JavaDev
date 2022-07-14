package per.study.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Exchanger用于两个线程之间交换数据
 */
public class ExchangerExample3 {

    /**
     * Actor
     */
    public static void main(String[] args) {
        final Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> {
            AtomicReference<Integer> value = new AtomicReference<>(1);
            try {
                while (true) {
                    value.set(exchanger.exchange(value.get()));
                    System.out.println("Thread A has value: " + value.get());
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "A").start();


        new Thread(() -> {
            AtomicReference<Integer> value = new AtomicReference<>(2);
            try {
                while (true) {
                    value.set(exchanger.exchange(value.get()));
                    System.out.println("Thread B has value: " + value.get());
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "B").start();

    }

}
