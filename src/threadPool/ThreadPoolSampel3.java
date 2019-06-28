package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 */
public class ThreadPoolSampel3 {
    public static void main(String[] args) {
        //调度器对象
        //ExecutorService用于管理线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单线程池
        for (int i = 1; i <= 1000; i++) {
            final int index = i;
            threadPool.execute(() -> System.out.println(Thread.currentThread().getName() + ":" + index));
        }
        try {
            Thread.sleep(1000); //给线程足够的运行时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //shutdown() 关闭线程池（等待所有线程完成）
        //shutdownNow() 代表立即终止线程池的运行，不等待线程，不推荐使用
        threadPool.shutdown();
    }
}
