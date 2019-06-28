package threadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 苏犇 on 2019/2/3. <br>
 */
public class ThreadPoolSampel4 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);//可调度线程池
        //延迟3秒执行一次run方法
//        scheduledThreadPool.schedule(() -> System.out.println("延迟3秒执行"), 3, TimeUnit.SECONDS);
        //Timer, 项目实际开发中scheduledThreadPool与Timer都不会用到，因为有成熟的调度矿机Quartz，或者Spring自带调度
        //Cron表达式
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println(new Date() + "延迟1秒执行，每三秒执行一次"), 1, 3, TimeUnit.SECONDS);
    }
}
