package per.study.thread.design.chapter4.observer;

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
