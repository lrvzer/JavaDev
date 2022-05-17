package per.study.thread.base.chapter1;

public class TryConcurrency {

    public static void main(String[] args) {
        new Thread("Tom") {
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread("hha") {
            @Override
            public void run() {
                writeData2File();
            }
        }.start();

        /*readFromDataBase();
        writeData2File();*/
    }

    private static void readFromDataBase() {
        // read data from database and handle it
        try {
            System.out.println("Begin read from db.");
            Thread.sleep(1000 * 10L);
            System.out.println("Read data done and start handle it");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The data handle finish and successfully");
    }

    private static void writeData2File() {
        try {
            System.out.println("Begin write to file.");
            Thread.sleep(1000 * 10L);
            System.out.println("write data done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The data wrie finish and successfully");
    }
}
