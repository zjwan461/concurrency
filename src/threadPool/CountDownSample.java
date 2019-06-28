package threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 苏犇 on 2019/2/3. <br>
 */
public class CountDownSample {
    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        CountDownLatch cdl = new CountDownLatch(10000); //CDL总数和操作数保持一致
        for (int i = 1; i <= 10000; i++) {
            final int index = i;
            threadPool.execute(() -> {
                synchronized (CountDownSample.class) {
                    try {
                        count = count + index;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        cdl.countDown();//计数器减1
                    }
                }
            });
        }
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        try {
            cdl.await(); //阻塞当前线程，直到cdl=0的时候才会继续往下执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        threadPool.shutdown();
    }
}
