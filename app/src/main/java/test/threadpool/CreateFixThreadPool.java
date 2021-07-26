package test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ncx on 2021/4/19
 */
public class CreateFixThreadPool {
    public static void main(String[] args) {
        ExecutorService fixThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            fixThreadPool.execute(() -> System.out.println("线程" + Thread.currentThread().getName() + "正在执行:" + taskId));
        }
    }
    /**
     * 线程pool-1-thread-1正在执行:1
     * 线程pool-1-thread-2正在执行:2
     * 线程pool-1-thread-3正在执行:3
     * 线程pool-1-thread-2正在执行:4
     * 线程pool-1-thread-1正在执行:6
     * 线程pool-1-thread-1正在执行:8
     * 线程pool-1-thread-3正在执行:5
     * 线程pool-1-thread-1正在执行:9
     * 线程pool-1-thread-2正在执行:7
     * 线程pool-1-thread-3正在执行:10
     */
}
