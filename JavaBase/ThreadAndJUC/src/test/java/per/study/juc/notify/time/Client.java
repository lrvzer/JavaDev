package per.study.juc.notify.time;

public class Client {

    public static void main(String[] args) {
        ProcessNotify processNotify = new ProcessNotify();
        Notify notify = new Notify(processNotify);

        processNotify.start();

        notify.start();
    }

}
