package per.study.juc.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    /**
     * Can't access the private field of object.
     */
    @Test(expected = IllegalAccessException.class)
    public void testPrivateFieldAccessError() {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1);
    }

    @Test
    public void testTargetObjectIsNull() {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");
        updater.compareAndSet(null, 0, 1);
    }

    @Test
    public void testFieldNameInvalid() {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i1");
        updater.compareAndSet(null, 0, 1);
    }

    @Test
    public void testFieldTypeInvalid() {
//        AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer.class, "i");
        AtomicReferenceFieldUpdater<TestMe2, Long> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Long.class, "i");
        TestMe2 me = new TestMe2();
        updater.compareAndSet(me, null, 1L);
    }

    @Test
    public void testFieldIsNotVolatile() {
        AtomicReferenceFieldUpdater<TestMe2, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(TestMe2.class, Integer.class, "i");
        TestMe2 me = new TestMe2();
        updater.compareAndSet(me, null, 1);
    }

    static class TestMe2 {
//        volatile Integer i;
        Integer i;
    }
}

/**
 * 1.想让类的属性操作具备原子性
 *  1.1 volatile修饰属性
 *  1.2 非private，protected（如果是当前类也可以是private or protected）
 *  1.3 类型必须一致
 *  1.4 其他
 * 2.不想使用锁（包括显示锁或者重量级锁synchronized）
 * 3.大量需要院子类型修饰的对象，相比较耗费内存
 */