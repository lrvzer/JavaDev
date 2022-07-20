package per.study.thread.design.chapter11;

public final class ActionContext {

    private static final ThreadLocal<Context> THREAD_LOCAL = ThreadLocal.withInitial(Context::new);

    private static class ContextHolder {
        private static final ActionContext actionContext = new ActionContext();
    }

    public Context getContext() {
        return THREAD_LOCAL.get();
    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public static void unLoad() {
        THREAD_LOCAL.remove();
    }

}
