package test.synctest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ncx on 2021/3/31
 */
public class ReadWriteTest {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String number = "0";

    public static void main(String[] args) {
        //创建 2 个 Reader 线程并从缓存中读取数据，和 1 个 Writer 将数据写入缓存中
        //写入操作在执行时，读取数据的操作会被阻塞
        Thread t1 = new Thread(new Reader(), "读线程 1");
        Thread t2 = new Thread(new Reader(), "读线程 2");
        Thread t3 = new Thread(new Writer(), "写线程");
        t1.start();
        t2.start();
        t3.start();
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                //使用读锁（ReadLock）将读取数据的操作加锁；
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "===> Number is " + number);
                lock.readLock().unlock();
            }
        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 7; i += 2) {
                try {
                    //使用写锁（WriteLock）将写入数据到缓存中的操作加锁。
                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName() + "正在写入" + number);
                    number = number.concat("" + i);
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }
}
