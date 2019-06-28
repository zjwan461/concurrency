package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 苏犇 on 2019/2/4. <br>
 */
public class AtomicIntegerSample {

    public static int user = 100;
    public static int total = 50000;
    public static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(user);
        for (int i = 0; i < total; i++) {
            final Integer index = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("下载总数为：" + count);
    }

    public static void add() {
        count.getAndIncrement();
    }
}
