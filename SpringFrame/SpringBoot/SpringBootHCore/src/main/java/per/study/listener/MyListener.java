package per.study.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("===事件===到达===" + event);
    }

}
