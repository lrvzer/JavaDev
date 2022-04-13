package per.study.juc.thread.chapter9;

import java.util.stream.Stream;

// 单生产者，单消费者
public class ProduceConsumerVersion3 {

    private int i = 0;
    final private Object LOCK = new Object();
    private volatile boolean isProduce = false;

    private void produce() {
        synchronized (LOCK) {
            while (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println(Thread.currentThread().getName() + "->" + i);
            LOCK.notifyAll();
            isProduce = true;
        }
    }

    private void consume() {
        synchronized (LOCK) {
            while (!isProduce){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + i);
            LOCK.notifyAll();
            isProduce = false;
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();

        Stream.of("P1", "P2").forEach(s -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, s).start();
        });

        Stream.of("C1", "C2").forEach(s -> {
            new Thread(() -> {
                while (true) {
                    pc.consume();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, s).start();
        });

    }

}
