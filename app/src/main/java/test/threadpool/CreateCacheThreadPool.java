package test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ncx on 2021/4/19
 */
public class CreateCacheThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            cachedThreadPool.execute(() -> {
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "正在执行:" + taskId);
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Thread.sleep(1000);
        }
        //关闭线程池
        cachedThreadPool.shutdown();
        /**
         * 线程pool-1-thread-3正在执行:3
         * 线程pool-1-thread-1正在执行:1
         * 线程pool-1-thread-2正在执行:2
         * 线程pool-1-thread-4正在执行:4
         * 线程pool-1-thread-5正在执行:5
         */
    }
}
