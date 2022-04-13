package per.study.juc.design.chapter18;

/**
 * 接受异步消息的主动对象
 */
public interface ActiveObject {
    /**
     * 拼接字符
     * @param count 拼接长度
     * @param fillChar 拼接字符
     * @return
     */
    Result makeString(int count, char fillChar);
    void displayString(String text);
}
