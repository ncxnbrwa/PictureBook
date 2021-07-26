package test.synctest;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by ncx on 2021/4/7
 * 自定义实现AQS锁
 */
public class MyLock {
    Sync sync = new Sync();

    public void lock() {
        //获取锁
        sync.acquire(1);
    }

    public void unlock() {
        //释放锁
        sync.release(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            //设置获取到锁状态
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            //设置锁状态为0,即无所状态
            setState(0);
            return true;
        }

    }

    static int count = 0;
    static MyLock myLock = new MyLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                myLock.lock();
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
            }
        };
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count:" + count);
    }
}
