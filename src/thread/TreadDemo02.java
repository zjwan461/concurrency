package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by 苏犇 on 2019/2/4. <br>
 */
public class TreadDemo02 {
    public static int users = 5;
    public static int downLoadNum = 50000;
    public static int count = 0;

    public static void count1() {
        long start_time = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Semaphore semaphore = new Semaphore(users);
        for (int i = 0; i < downLoadNum; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    synchronized (TreadDemo02.class) {
                        count++;
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        long end_time = System.currentTimeMillis();
        System.out.println("计算结果为：" + count);
        System.out.println("计算用时：" + (end_time - start_time) + "ms");
    }

    public static void count2() {
        long start_time = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(downLoadNum);
        for (int i = 0; i < downLoadNum; i++) {
            executorService.execute(() -> {
                synchronized (TreadDemo02.class) {
                    count++;
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end_time = System.currentTimeMillis();
        System.out.println("计算结果为：" + count);
        System.out.println("计算用时：" + (end_time - start_time) + "ms");
    }


}
