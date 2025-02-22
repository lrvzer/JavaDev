package per.study.thread.design.chapter4.observer;

public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }


    public abstract void update();

}
