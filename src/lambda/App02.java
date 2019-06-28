package lambda;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Created by 苏犇 on 2019/2/3. <br>
 * Stream API、多线程 执行从1 + 2+ 3+...+1000000 的性能对比
 */
public class App02 {
    public static void main(String[] args) {
        count1();
        System.out.println("------------------");
        count2();
    }

    static void count1() {
        long start_time = System.currentTimeMillis();
        long count = Stream.iterate(1, x -> x + 1).limit(1000000).mapToInt(x -> x).sum();
        long end_time = System.currentTimeMillis();
        System.out.println("count1用时为：" + (end_time - start_time) + "ms");
        System.out.println("count1计算总数为：" + count);
    }

    static int count = 0;

    static void count2() {
        long start_time = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        CountDownLatch cdl = new CountDownLatch(1000000);
        threadPool.execute(() -> {
            for (int i = 1; i <= 1000000; i++) {
                final int index = i;
                synchronized (App02.class) {
                    try {
                        count = count + index;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        cdl.countDown();
                    }
                }
            }
        });

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end_time = System.currentTimeMillis();
        System.out.println("count2用时为：" + (end_time - start_time) + "ms");
        System.out.println("count2计算总数为: " + count);
        threadPool.shutdown();

    }
}
