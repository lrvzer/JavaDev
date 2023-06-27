package per.study.listeners;

import per.study.events.AbstractContextEvent;
import per.study.events.ContextRunningEvent;

public class ContextRunningEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextRunningEvent) {
            System.out.println("容器启动开始运行");
            try {
                Thread.sleep(3000);
                System.out.println("容器运行结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
