package test.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * Created by ncx on 2021/5/13
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MoonRunnable(),"MoonThread");
        thread.start();
        //留给MoonThread线程时间来感知中断从而结束
        TimeUnit.MILLISECONDS.sleep(10);
        thread.interrupt();
    }

    public static class MoonRunnable implements Runnable {
        private long i;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
                System.out.println("i=" + i);
            }
            System.out.println("stop");
        }
    }
}
