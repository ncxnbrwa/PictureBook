package test.synctest;

import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用阻塞队列实现的生产者-消费者模式
 * 阻塞队列实现无须单独考虑同步和线程间通信的问题，其实现起来很简单。
 */
public class ConsumerProducerTest2 {
    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);

    public static void main(String[] args) {
        ConsumerProducerTest2 test = new ConsumerProducerTest2();
        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();
        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    queue.notify();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    queue.notify();
                }
            }
        }
    }
}
