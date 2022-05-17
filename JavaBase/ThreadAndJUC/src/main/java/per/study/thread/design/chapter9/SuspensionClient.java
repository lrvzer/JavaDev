package per.study.thread.design.chapter9;

public class SuspensionClient {

    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();

        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();


        Thread.sleep(10000);
        serverThread.close();
    }

}
