package lambda;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by 苏犇 on 2019/2/4. <br>
 */
public class App03 {
    public static void count1() {
        long start_time = System.currentTimeMillis();
        Stream.iterate(2, x -> x + 1).limit(10000).filter(x -> {
            boolean flag = true;
            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }).forEach(System.out::println);
        long end_time = System.currentTimeMillis();
        System.out.println("耗时：" + (end_time - start_time) + "ms");
    }

    public static void count2() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 2; i <= 10000; i++) {
            Callable<Boolean> c = new Callable<Boolean>() {
                int num;

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                boolean flag = true;

                @Override
                public Boolean call() throws Exception {
                    for (int i = 2; i < num; i++) {
                        if (num % i == 0) {
                            flag = false;
                            break;
                        }
                    }
                    return flag;
                }
            };
            Future<Boolean> result = executorService.submit(c);
            try {
                boolean r = result.get();
                if (r) {
                    System.out.println(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        count2();
    }
}
