package test.synctest;

import android.animation.ObjectAnimator;

import java.util.concurrent.locks.ReentrantLock;

import test.dailitest.People;

/**
 * Created by ncx on 2021/3/31
 * 公平锁会完全按照顺序运行线程
 */
public class FairLockTest implements Runnable {
    private int sharedNumber = 0;
    //创建公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (sharedNumber < 20) {
            lock.lock();
            try {
                sharedNumber++;
                System.out.println(Thread.currentThread().getName() + "获得锁,sharedNumber is " + sharedNumber);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLockTest flt = new FairLockTest();
        Thread t1 = new Thread(flt, "t1");
        Thread t2 = new Thread(flt, "t2");
        Thread t3 = new Thread(flt, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
