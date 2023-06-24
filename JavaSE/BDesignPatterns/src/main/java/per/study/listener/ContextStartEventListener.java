package per.study.listener;

public class ContextStartEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("容器启动，启动时间为：" + event.getTimestamp());
        }
    }
}
