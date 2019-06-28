package threadPool;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 苏犇 on 2019/2/4. <br>
 */
public class CopyWriteArrayListSample {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
        //写复制列表
        List<Integer> list = new CopyOnWriteArrayList<>();
        for (int i=0;i<1000;i++){
            list.add(i);
        }

        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()){
            Integer i = itr.next();
            list.remove(i);
        }
        System.out.println(list);
    }
}
