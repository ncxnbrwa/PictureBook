package test.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ncx on 2021/4/19
 */
public class NonCoreThreadPool {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            threadPool.execute(() -> {
                try {
                    System.out.println("当前线程" + Thread.currentThread().getName() + ":" + taskId);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("此时等待队列中有" + threadPool.getQueue().size() + "个元素");
        }
        threadPool.shutdown();
    }
}
