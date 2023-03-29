#### sleep与yield的区别

```
sleep会导致当前线程暂停指定的时间，没有CPU时间片的消耗。
yield只是对CPU调度器的一个提示，如果CPU调度器没有忽略这个提示，它会导致线程上下文的切换。
sleep会使线程短暂block，会在给定的时间内释放CPU资源。
yield会使RUNNING状态的Thread进入RUNNABLE状态（如果CPU调度器没有忽略这个提示的话）。
sleep几乎百分之百地完成了给定时间的休眠，而yield的提示并不能一定担保。
一个线程sleep另一个线程调用interrupt会捕获到中断信号，而yield则不会
```

Interrupt
```
如下方法的调用会使得当前线程进入阻塞状态，而调用当前线程的interrupt方法，就可以打断阻塞。
Object的wait方法。
Object的wait（long）方法。
Object的wait（long，int）方法。
Thread的sleep（long）方法。
Thread的sleep（long，int）方法。
Thread的join方法。
Thread的join（long）方法。
Thread的join（long，int）方法。
InterruptibleChannel的io操作。
Selector的wakeup方法。
其他方法。
```

```
Thread.interrupted()
测试当前线程是否已经中断（静态方法）。如果连续调用该方法，则第二次调用将返回false。
在api文档中说明interrupted()方法具有清除状态的功能。执行后具有将状态标识清除为false的功能。

Thread.currentThread().isInterrupted()
测试线程是否已经中断，但是不能清除状态标识。

Thread.currentThread().interrupt();
中断当前线程
```

```text
使用synchronized需要注意的问题
（1）与monitor关联的对象不能对空
（2）synchronized作用域太大，应该尽可能的之作用于共享资源（数据）的读写作用域
（3）不同的monitor企图锁相同的内容
（4）多个锁的交叉导致死锁
```


#### wait和sleep
```text
wait和sleep方法都可以使线程进入阻塞状态。
wait和sleep方法均是可中断方法，被中断后都会收到中断异常。
wait是Object的方法，而sleep是Thread特有的方法。
wait方法的执行必须在同步方法中进行，而sleep则不需要。
线程在同步方法中执行sleep方法时，并不会释放monitor的锁，而wait方法则会释放monitor的锁。
sleep方法短暂休眠之后会主动退出阻塞，而wait方法（没有指定wait时间）则需要被其他线程中断后才能退出阻塞。
```
