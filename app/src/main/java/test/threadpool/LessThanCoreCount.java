package test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ncx on 2021/4/19
 */
public class LessThanCoreCount {
    public static void main(String[] args) throws Exception {
        ExecutorService fixThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixThreadPool.execute(() -> {
                try {
                    System.out.println("当前线程" + Thread.currentThread().getName() + ":" + taskId);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(2000);
        }
        fixThreadPool.shutdown();
    }
}
