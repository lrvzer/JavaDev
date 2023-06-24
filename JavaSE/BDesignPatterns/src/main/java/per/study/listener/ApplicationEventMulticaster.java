package per.study.listener;

// 事件发布器接口
public interface ApplicationEventMulticaster {
    void addContextListener(ContextListener<?> listener);

    void removeContextListener(ContextListener<?> listener);

    void removeAllListeners();

    void multicastEvent(AbstractContextEvent event);
}
