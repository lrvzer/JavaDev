package per.study.dp.context;

import java.util.concurrent.ConcurrentHashMap;

public final class ApplicationContext {

    private ConcurrentHashMap<Thread, ActionContext> contexts = new ConcurrentHashMap<>();

    private static class Holder {
        private static ApplicationContext instance = new ApplicationContext();
    }

    public static ApplicationContext getContext() {
        return Holder.instance;
    }

    public ActionContext getActionContext() {
        ActionContext actionContext = contexts.get(Thread.currentThread());
        if (actionContext == null) {
            actionContext = new ActionContext();
            contexts.put(Thread.currentThread(), actionContext);
        }
        return actionContext;
    }

}
