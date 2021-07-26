package test.synctest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ncx on 2021/6/9
 * 模拟支付场景,使用重入锁
 */
class AliPay {
    private double[] accounts;
    private Lock alipayLock;
    private Condition condition;

    public AliPay(int n, double money) {
        accounts = new double[n];
        alipayLock = new ReentrantLock();
        condition = alipayLock.newCondition();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = money;
        }
    }

    public void transfer(int from, int to, int amount) {
        alipayLock.lock();
        try {
            //模拟转账的操作,当余额不足时,等待这个账户重置
            while (accounts[from] < amount) {
                //一旦一个线程调用 await 方法，它就会进入该条件的等待集并处于阻塞状态
                //直到另一个线程调用了同一个条件的signalAll方法时为止
                //有点类似于Object.wait()
                condition.await();
            }
            accounts[from] = accounts[from] - amount;
            accounts[to] = accounts[to] + amount;
            //类似于Object.notifyAll()
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            alipayLock.unlock();
        }
    }
}
