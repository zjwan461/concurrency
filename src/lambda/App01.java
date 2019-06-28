package lambda;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 */
public class App01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "启动"), "线程一");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "启动");
            }
        },"线程二");
        t2.start();
    }
}
