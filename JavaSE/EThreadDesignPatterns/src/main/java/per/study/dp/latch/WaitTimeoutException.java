package per.study.dp.latch;

/**
 * 当子任务线程执行超时的时候将会抛出该异常
 */
public class WaitTimeoutException extends Exception {
    public WaitTimeoutException(String message) {
        super(message);
    }
}
