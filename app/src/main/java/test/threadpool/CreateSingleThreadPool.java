package test.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ncx on 2021/4/19
 */
public class CreateSingleThreadPool {
    public static void main(String[] args) {
        //创建单线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            try {
                final int taskId = i;
                singleThreadExecutor.submit(() -> System.out.println("线程" + Thread.currentThread().getName() + "正在执行:" + taskId));
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /**
         * 日志
         * 线程pool-1-thread-1正在执行:0
         * 线程pool-1-thread-1正在执行:1
         * 线程pool-1-thread-1正在执行:2
         * 线程pool-1-thread-1正在执行:3
         * 线程pool-1-thread-1正在执行:4
         * 说明所有任务都是在同一线程中执行的
         */
    }
}
