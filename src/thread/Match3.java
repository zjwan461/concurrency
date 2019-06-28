package thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 * 通过创建线程池来启动线程
 */
public class Match3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runner3 run1 = new Runner3();
        run1.setName("刘翔");
        Runner3 run2 = new Runner3();
        run2.setName("博尔特");
        Runner3 run3 = new Runner3();
        run3.setName("加特林");
        Future<Integer> result1 = executorService.submit(run1);
        Future<Integer> result2 = executorService.submit(run2);
        Future<Integer> result3 = executorService.submit(run3);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(run1.getName() + "累计跑了" + result1.get() + "米");
        System.out.println(run2.getName() + "累计跑了" + result2.get() + "米");
        System.out.println(run3.getName() + "累计跑了" + result3.get() + "米");
    }
}

class Runner3 implements Callable<Integer> {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        Integer speed = new Random().nextInt(100);
        Integer distance = 0;
        for (int i = 1; i < 100; i++) {
            Thread.sleep(100);
            distance += i * speed;
            System.out.println(this.getName() + "已前进" + distance + "米，速度为：" + speed + "米/秒");
        }
        return distance;
    }
}