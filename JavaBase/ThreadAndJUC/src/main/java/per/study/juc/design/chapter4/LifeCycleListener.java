package per.study.juc.design.chapter4;

public interface LifeCycleListener {
    void onEvent(ObservableRunnable.RunnableEvent event);
}
