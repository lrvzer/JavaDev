package per.study.thread.design.workerthread.demo1;

public abstract class InstructionBook {

    public final void create() {
        firstProcess();
        secondProcess();
    }

    protected abstract void firstProcess();
    protected abstract void secondProcess();

}
