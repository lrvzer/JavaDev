package per.study.thread.design.workerthread.demo1;

public class Production extends InstructionBook {

    private final int prodID;

    public Production(int prodID) {
        this.prodID = prodID;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the " + prodID + " first process.");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + prodID + " second process.");
    }
}
