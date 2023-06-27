package per.study.listeners;

import per.study.events.AbstractContextEvent;
import per.study.events.ContextStartEvent;

public class ContextStartEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("容器启动，启动时间为：" + event.getTimestamp());
        }
    }
}
