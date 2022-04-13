package per.study.juc.design.chapter11;

public class ExecutionTask implements Runnable{

    private final QueryFromDBAction queryFromDBAction = new QueryFromDBAction();
    private final QueryFromHttpAction queryFromHttpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        ActionContext.unLoad();

        queryFromDBAction.execute();
        System.out.println("The name query successful");
        queryFromHttpAction.execute();
        System.out.println("The card id query successful");

        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The Name is [" + context.getName() + "] and CardID [" + context.getCardID() + "]");
    }
}
