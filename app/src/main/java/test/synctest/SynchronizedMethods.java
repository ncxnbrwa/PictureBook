package test.synctest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedMethods {
    private Object lock = new Object();
    private ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedMethods s1 = new SynchronizedMethods();
        Thread t1 = new Thread(s1::printLog);
//        Thread t1 = new Thread(s1::printLog2);
        Thread t2 = new Thread(s1::printLog);
//        Thread t2 = new Thread(s1::printLog2);
        t1.start();
        t2.start();
    }

    //    public void printLog() {
    public synchronized void printLog() { //这样写锁对象是当前实例对象，因此只有同一个实例对象调用此方法才会产生互斥效果，不同实例对象之间不会有互斥效果
//    public static synchronized void printLog() {//这样写的话则锁对象是当前类的 Class 对象
//        synchronized (lock){}
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printLog2() {
        try {
            reentrantLock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
