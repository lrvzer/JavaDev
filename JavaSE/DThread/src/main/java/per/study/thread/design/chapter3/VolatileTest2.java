package per.study.thread.design.chapter3;

/* 出现CPU缓存不一致问题 */

/* 思路：解决CPU缓存不一致问题
    1.给数据总线加锁
        总线（数据总线，地址总线，控制总线）
        LOCK#
    2.CPU高速缓存一致性协议
        Intel MESI
        核心思想
            1.当CPU写入数据的时候，如果发现该变量被共享（也就是说，在其他CPU中也存在该变量的副本），会发出一个信号，通知其他CPU该变量的缓存无效
            2.当其他CPU访问该变量的时候，重新从主内存中获取

    3.并发编程中三个比较重要的概念
        1.原子性：保证一个操作或者多个操作，要么都成功，要么都失败，中间不能由于任何操作的因素中断
            对基本数据类型的变量读取和赋值是保证了原子性的，要么都成功，要么都失败，中间不能由于任何操作的因素中断
            a = 10;        原子性
            b = a;         不满足，1.read a 2.assign to b
            c++;           不满足，1.read c 2.add 3.assign to c
            c = c+1;       不满足，1.read c 2.add 3.assign to c

        2.可见性
            使用volatile关键字保证可见性

        3.有序性（顺序性）
            happens-before relationship
            3.1 单线程法则：一个线程内，代码的执行顺序，编写在前面的发生在编写在后面的之前
            3.2 监视器锁法则：unlock必须发生在lock之后
            3.3 volatile修饰的变量，对一个变量的写操作先于该变量的读操作
            3.4 传递规则，操作A先于B，B先于C，那么A肯定先于C
            3.5 线程启动规则：在一个线程里，对Thread.start()的调用会先于Thread.run()
            3.6 线程中断规则：interrupt这个动作，必须发生在捕获该动作之前
            3.7 对象销毁规则：一个对象的初始化必须发生在finalize之前
            3.8 线程终结规则：所有的操作都发生在线程死亡之前

    4.volatile关键字
        1）一旦一个共享变量被volatile修饰，具备两层语义
            1.保证了不同线程间的可见性
            2.禁止对其进行重排序，也就是保证了有序性
            3.并未保证原子性
        2）保证重排序的时候，不会把后面的指令放到屏障的前面，也不会把前面的放到后面
        3）强制对缓存的修改操作立刻写入主存
        4）如果是写操作，它会导致其他CPU中的缓存失效

    5.volatile使用场景
        1.状态量标记
            volatile boolean start = true;
            while (start) {
                //
            }

            void close() {
                start = false;
            }
        2.保证屏障前后的一致性
            volatile boolean init;
            ---------- Thread-1 ----------
            obj = createObj();
            init = true;

            ---------- Thread-2 ----------
            while (!init) {
                sleep();
            }

            useTheObj(obj);
            ------------------------------
 */
public class VolatileTest2 {

    private static volatile int INIT_VALUE = 0;
    private static final int MAX_LIMIT = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T1->" + (++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("T2->" + (++INIT_VALUE));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-2").start();
    }

}
