package test.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ncx on 2021/4/19
 */
public class ThreadPoolOOM {
    public static void main(String[] args) {
        ThreadPoolExecutor fixThreadPool= (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 0; i < 1000000; i++) {
            final int taskId = i;
            fixThreadPool.execute(()->{
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "正在执行:" + taskId);
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            fixThreadPool.shutdownNow();
        }
    }
}
