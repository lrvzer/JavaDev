package per.study.thread.base.chapter5.demo;

import java.util.List;

/**
 * 不管是Thread的run方法，还是Runnable接口，都是void返回类型，
 * 如果想通过某个线程的运行得到结果，就需要定义一个返回的接口
 */
public interface FightQuery {
    /**
     * 获取信息
     * @return
     */
    List<String> get();
}
