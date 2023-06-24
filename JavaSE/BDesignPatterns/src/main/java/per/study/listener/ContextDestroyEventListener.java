package per.study.listener;

public class ContextDestroyEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("容器销毁，销毁时间为：" + event.getTimestamp());
        }
    }
}
