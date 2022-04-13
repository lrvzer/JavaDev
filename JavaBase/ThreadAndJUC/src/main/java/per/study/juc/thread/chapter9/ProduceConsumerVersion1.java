package per.study.juc.thread.chapter9;

// 生产的产品多，消费的产品少
public class ProduceConsumerVersion1 {
    private int i = 1;
    final private Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            System.out.println("P->" + (i++));
        }
    }

    private void consume() {
        synchronized (LOCK) {
            System.out.println("C->" + i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();

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
