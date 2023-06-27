package per.study.caster;

import per.study.events.AbstractContextEvent;
import per.study.listeners.ContextListener;

// 事件发布器接口
public interface ApplicationEventMulticaster {
    void addContextListener(ContextListener<?> listener);

    void removeContextListener(ContextListener<?> listener);

    void removeAllListeners();

    void multicastEvent(AbstractContextEvent event);
}
