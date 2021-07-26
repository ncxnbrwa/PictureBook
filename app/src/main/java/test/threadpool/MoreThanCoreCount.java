package test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by ncx on 2021/4/19
 */
public class MoreThanCoreCount {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor fixThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fixThreadPool.execute(() -> {
                try {
                    System.out.println("当前线程" + Thread.currentThread().getName() + ":" + taskId);
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("此时等待队列中有" + fixThreadPool.getQueue().size() + "个元素");
            Thread.sleep(500);
        }
        fixThreadPool.shutdown();
    }
    /**
     * 输出日志
     * 此时等待队列中有0个元素
     * 当前线程pool-1-thread-1:1
     * 此时等待队列中有0个元素
     * 当前线程pool-1-thread-2:2
     * 此时等待队列中有1个元素
     * 此时等待队列中有2个元素
     * 此时等待队列中有3个元素
     * 当前线程pool-1-thread-1:3
     * 当前线程pool-1-thread-2:4
     * 当前线程pool-1-thread-1:5
     */
}
