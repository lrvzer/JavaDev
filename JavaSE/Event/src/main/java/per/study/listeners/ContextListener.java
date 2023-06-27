package per.study.listeners;

import per.study.events.AbstractContextEvent;

/**
 * 容器监听器接口，被所有的具体容器监听器类实现
 **/
public interface ContextListener<T extends AbstractContextEvent> extends EventListener {
    /**
     * handler an application event
     *
     * @param event
     */
    void onApplicationEvent(T event);
}
