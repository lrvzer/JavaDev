package per.study.thread.design.chapter6;

/**
 * ReadWriteLock design pattern
 * Reader-Writer design pattern
 */
public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData data = new SharedData(10);
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();
        new ReaderWorker(data).start();

        new WriterWorker(data, "qwertyuiop").start();
        new WriterWorker(data, "ASDSDSDSDS").start();

    }


}
