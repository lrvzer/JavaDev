package per.study.caster;

import per.study.events.AbstractContextEvent;
import per.study.listeners.ContextListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事件发布器的默认实现类
 **/
public class SimpleApplicationEventMulticaster implements ApplicationEventMulticaster {
    private boolean async = false;

    private Executor taskExecutor
            = new ThreadPoolExecutor(
            5,
            5,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    private List<ContextListener<?>> contextListeners = new ArrayList<>();

    @Override
    public void addContextListener(ContextListener<?> listener) {
        contextListeners.add(listener);
    }

    @Override
    public void removeContextListener(ContextListener<?> listener) {
        contextListeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        contextListeners.clear();
    }

    @Override
    public void multicastEvent(AbstractContextEvent event) {
        doMulticastEvent(contextListeners, event);
    }

    private void doMulticastEvent(List<ContextListener<?>> contextListeners, AbstractContextEvent event) {
        for (ContextListener<?> contextListener : contextListeners) {
            // 异步广播事件
            if (async) {
                taskExecutor.execute(() -> invokeListener(contextListener, event));
            } else {
                invokeListener(contextListener, event);
            }
        }
    }

    private void invokeListener(ContextListener listener, AbstractContextEvent event) {
        listener.onApplicationEvent(event);
    }

    public void setAsync(boolean async) {
        this.async = async;
    }
}
