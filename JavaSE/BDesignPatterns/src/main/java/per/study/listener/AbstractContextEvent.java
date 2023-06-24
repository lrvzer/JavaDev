package per.study.listener;

/**
 * AbstractContextEvent是容器事件的基本抽象类，因为事件也可以携带数据，
 * 因此这里定义了一个timestamp属性，用来记录事件的发生时间。
 **/
public class AbstractContextEvent implements Event {

    private static final long serialVersionUID = 6381853801116581288L;
    private final long timestamp = System.currentTimeMillis();

    public long getTimestamp() {
        return timestamp;
    }

}
