package test.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ncx on 2021/4/19
 */
public class CreateScheduledThreadPool {
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
//        scheduledThreadPool.schedule(new WorkRunnable(),2000,TimeUnit.MILLISECONDS);
//        scheduledThreadPool.scheduleAtFixedRate(new WorkRunnable(), 500, 500, TimeUnit.MILLISECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(new WorkRunnable(), 500, 500, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
        scheduledThreadPool.shutdown();
    }

    private static class WorkRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Date now = new Date();
                System.out.println("线程" + Thread.currentThread().getName() + "报时:" + now);
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
