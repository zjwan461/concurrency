package threadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by 苏犇 on 2019/2/3. <br>
 */
public class SemaPhoreSample2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= 20; i++) {
            final int index = i;
            threadPool.execute(() -> {
                try {
                    ////尝试获取一次信号量，6秒钟内，获取到返回true，否则返回false
                    if (semaphore.tryAcquire(6, TimeUnit.SECONDS)) {
                        play();
                        semaphore.release(); //执行完成后释放这个信号量
                    } else {
                        System.out.println(Thread.currentThread().getName() + ":sorry 服务器已满，请稍后再试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
    }

    public static void play() {
        try {
            System.out.println(new Date() + " " + Thread.currentThread().getName() + "获得紫荆之巅服务器进入资格");
            Thread.sleep(2000);
            System.out.println(new Date() + " " + Thread.currentThread().getName() + "退出服务器");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
