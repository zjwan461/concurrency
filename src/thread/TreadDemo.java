package thread;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 */
public class TreadDemo {
    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        Thread t1 = new Thread(demo01, "线程一");
        Demo01 demo02 = new Demo01();
        Thread t2 = new Thread(demo02, "线程二");
        t1.start();
        t2.start();
    }
}

class Demo01 implements Runnable {

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
    private static Integer count = 0;

    @Override
    public void run() {
        synchronized (this) {

            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "：" + count++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static int getCout() {
        return count;
    }

}
