package threadPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 苏犇 on 2019/2/3. <br>
 */
public class CycleBarrierSample {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 1; i <= 20; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(() -> {
                go();
            });
        }
        executorService.shutdown();
    }

    private static void go() {
        System.out.println(Thread.currentThread().getName() + ":准备就绪");
        try {
            cyclicBarrier.await();// 设置屏障点，当累计5个线程都准备好后，才运行后面的代码
            System.out.println(Thread.currentThread().getName() + ":开始运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
