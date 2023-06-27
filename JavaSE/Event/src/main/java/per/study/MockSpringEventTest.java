package per.study;

import per.study.caster.ApplicationEventMulticaster;
import per.study.caster.SimpleApplicationEventMulticaster;
import per.study.events.*;
import per.study.listeners.ContextDestroyEventListener;
import per.study.listeners.ContextRunningEventListener;
import per.study.listeners.ContextStartEventListener;

/**
 * @Description
 * @Author: Lrwei
 * @Date: 2023/6/16
 **/
public class MockSpringEventTest {
    public static void main(String[] args) {
        testContextLifecycleEventInSync();
//        testContextLifecycleEventInAsync();
    }

    private static void testContextLifecycleEventInSync() {
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());

        eventMulticaster.multicastEvent(new ContextStartEvent());
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        eventMulticaster.multicastEvent(new ContextDestroyEvent());
    }

    private static void testContextLifecycleEventInAsync() {
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());

        ((SimpleApplicationEventMulticaster) eventMulticaster).setAsync(true);

        eventMulticaster.multicastEvent(new ContextStartEvent());
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        eventMulticaster.multicastEvent(new ContextDestroyEvent());
    }

}
