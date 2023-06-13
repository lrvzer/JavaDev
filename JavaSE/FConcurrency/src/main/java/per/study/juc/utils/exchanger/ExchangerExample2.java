package per.study.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

/**
 * Exchanger用于两个线程之间交换数据
 */
public class ExchangerExample2 {

    /**
     * Actor
     */
    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object send = new Object();
            System.out.println("A will send the object" + send);

            try {
                Object ret = exchanger.exchange(send);
                System.out.println("A received the object " + ret);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "A").start();


        new Thread(() -> {
            Object send = new Object();
            System.out.println("B will send the object" + send);

            try {
                Object ret = exchanger.exchange(send);
                System.out.println("B received the object " + ret);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "B").start();

    }

}
