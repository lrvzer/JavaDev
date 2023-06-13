package per.study.thread.base.chapter9.demo1;

import java.util.LinkedList;

/**
 * EventQueue
 * 队列满——最多可容纳多少个Event，好比一个系统最多同时能够受理多少业务一样；
 * 队列空——当所有的Event都被处理并且没有新的Event被提交的时候，此时队列将是空的状态；
 * 有Event但是没有满——有新的Event被提交，但是此时没有到达队列的上限。
 */
public class EventQueue {

    private final int max; // 队列大小设置
    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private static final int DEFAULT_MAX_EVENT = 10; // 队列默认大小

    public EventQueue(int max) {
        this.max = max;
    }

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    /**
     * 生产event
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                try {
                    console("the queue is full.");
                    eventQueue.wait(); // 队满等待资源消耗后唤醒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            console("the new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    /**
     * 消费event
     */
    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                try {
                    console("the queue is empty.");
                    eventQueue.wait(); // 队空等待生产后唤醒
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("the event " + event + " is handled.");
            return event;
        }
    }

    /**
     * 打印日志
     * @param msg
     */
    private void console(String msg) {
        System.out.printf("%s: %s\n", Thread.currentThread().getName(), msg);
    }


    static class Event {

    }

}
