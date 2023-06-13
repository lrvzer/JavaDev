package per.study.thread.design.chapter11;

public class QueryFromHttpAction {

    private String getCardID(String name) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name + ": 12345" + Thread.currentThread().getId();
    }

    public void execute() {
        try {
            Thread.sleep(1000L);
            Context context = ActionContext.getActionContext().getContext();
            String name = context.getName();
            String cardID = getCardID(name);
            context.setCardID(cardID);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
