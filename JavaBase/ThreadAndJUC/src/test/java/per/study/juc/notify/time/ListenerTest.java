package per.study.juc.notify.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version v1.0
 * @className ListenerTest
 * @Author alan
 * @Date2021/9/23 15:59
 **/
public class ListenerTest {
    interface MyListener{
        void notifies(String message);
    }

    //等待监听器告诉你信息
    class LrwThread extends Thread implements MyListener{

        String message;
        ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();

        @Override
        public void notifies(String message) {
                this.message=message;
                lock.lock();
                condition.signal();
                lock.unlock();
        }

        public void run(){


            try{
                System.out.println("李荣为等待张志飞事情做完");
                lock.lock();
                condition.await(2000, TimeUnit.SECONDS);
            } catch(Exception e){
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("李荣伟监听完毕，收到张志飞发送的："+message);
        }
    }

    //做事情的线程，做好后，通知
    class zzfThread extends Thread{
        MyListener listener;
        public zzfThread(MyListener listener){
            this.listener=listener;
        }

        public void run(){
            System.out.println("张志飞正在做事");
            try{
                Thread.sleep(3000);
            } catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("张志飞做完事情了，现在通知李荣为");
            listener.notifies("李荣为你现在开始做事");

        }
    }

    public static void main(String[] args) {
        ListenerTest listenerTest = new ListenerTest();
        LrwThread thread=listenerTest.new LrwThread();
        thread.start();
        try{
            Thread.sleep(10);
        } catch(Exception e){
            e.printStackTrace();
        }

        Thread thread1=listenerTest.new zzfThread(thread);
        thread1.start();
    }
}
