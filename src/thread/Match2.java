package thread;

import java.util.Random;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 */
public class Match2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner2());
        t1.setName("刘翔");
        Thread t2 = new Thread(new Runner2(), "博尔特");
        Thread t3 = new Thread(new Runner2(), "加特林");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Runner2 implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "已前进" + speed * i + "米，" + speed + "米/秒");
        }
    }
}
