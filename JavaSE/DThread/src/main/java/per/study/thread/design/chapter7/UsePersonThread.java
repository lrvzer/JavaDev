package per.study.thread.design.chapter7;

public class UsePersonThread extends Thread{

    private final Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " print " + person);
        }
    }
}
