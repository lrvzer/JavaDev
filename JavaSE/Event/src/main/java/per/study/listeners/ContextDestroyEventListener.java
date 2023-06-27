package per.study.listeners;

import per.study.events.AbstractContextEvent;
import per.study.events.ContextDestroyEvent;

public class ContextDestroyEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextDestroyEvent) {
            System.out.println("容器销毁，销毁时间为：" + event.getTimestamp());
        }
    }
}
