package per.study.juc.thread.chapter9;

// 单生产者，单消费者
public class ProduceConsumerVersion2 {

    private int i = 0;
    final private Object LOCK = new Object();
    private volatile boolean isProduce = false;

    private void produce() {
        synchronized (LOCK) {
            if (isProduce) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                LOCK.notify();
                isProduce = true;
            }
        }
    }

    private void consume() {
        synchronized (LOCK) {
            if (isProduce) {
                System.out.println("C->" + i);
                LOCK.notify();
                isProduce = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();

        new Thread(() -> {
            while (true) {
                pc.produce();
            }
        }, "P").start();

        new Thread(() -> {
            while (true) {
                pc.consume();
            }
        }, "C").start();
    }

}
