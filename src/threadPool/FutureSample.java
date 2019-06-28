package threadPool;

import java.util.concurrent.*;

/**
 * Created by 苏犇 on 2019/2/4. <br>
 */
public class FutureSample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 2; i <= 10000; i++) {
            Computor c = new Computor();
            c.setNum(i);
            //Future是对用于计算的线程进行监听，因为计算是在其他线程总执行的，所以这个返回结果的过程是异步的
            Future<Boolean> result = executorService.submit(c);//将c对象提交给线程池，如有空闲对象立即执行Call方法
            try {
                Boolean r = result.get(); //用于获取返回值，如果线程内部的call没有执行完成，则进入等待状态，直到计算完成
                if (r == true) {
                    System.out.println(c.getNum());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}

class Computor implements Callable<Boolean> {

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Boolean call() throws Exception {
        boolean isprime = true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                isprime = false;
                break;
            }
        }
        return isprime;
    }
}