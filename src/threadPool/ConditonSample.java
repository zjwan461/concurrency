package threadPool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 苏犇 on 2019/2/4. <br>
 */
public class ConditonSample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();//Condition 对象必须配合lock一起使用
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(() -> {
            lock.lock();//加锁
            try {
                c1.await();//阻塞当前线程,c1.singal的时候线程激活继续执行
                Thread.sleep(1000);
                System.out.println("粒粒皆辛苦");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            lock.lock();//加锁
            try {
                c2.await();//阻塞当前线程,c1.singal的时候线程激活继续执行
                Thread.sleep(1000);
                System.out.println("谁知盘中餐");
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            lock.lock();//加锁
            try {
                c3.await();//阻塞当前线程,c1.singal的时候线程激活继续执行
                Thread.sleep(1000);
                System.out.println("汗滴禾下土");
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("锄禾日当午");
                c3.signal(); // T3线程继续执行
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

    }
}
