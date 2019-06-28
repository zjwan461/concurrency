package thread;

import java.util.Random;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 * 使用继承Thread的方式来实现多线程
 */
public class Match1 {

    public static void main(String[] args) {
        Runner liuxiang = new Runner();
        liuxiang.setName("刘翔");
        liuxiang.start();//启动线程
    }

}

class Runner extends Thread {
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        Integer speed = new Random().nextInt(100);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "已前进" + (i * speed) + "米," + speed + "米/秒");
        }
    }
}
