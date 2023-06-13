package per.study.thread.design.workerthread.demo2;

public class Request {

    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString() {
        return "Request=> No. " + number + " Name. " + name;
    }

}
