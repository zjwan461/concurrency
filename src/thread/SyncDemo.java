package thread;

import java.util.Random;

/**
 * Created by 苏犇 on 2019/2/2. <br>
 */
public class SyncDemo {

    public static void main(String[] args) {
        Couple c = new Couple();
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    int r = new Random().nextInt(2);
                    if (r % 2 == 0) {
                        Couple.first();
                    } else {
                        Couple.second();
                    }
                }
            }.start();
        }
    }
}

class Couple {
    Object obj = new Object();

    public static synchronized void first() {
//        synchronized (obj) {
            System.out.printf("琴");
            System.out.printf("瑟");
            System.out.printf("琵");
            System.out.printf("琶");
            System.out.println();
        }
//    }

    public static void second() {
        synchronized (Couple.class) {
            System.out.printf("魑");
            System.out.printf("魅");
            System.out.printf("魍");
            System.out.printf("魉");
            System.out.println();
        }
    }
}
