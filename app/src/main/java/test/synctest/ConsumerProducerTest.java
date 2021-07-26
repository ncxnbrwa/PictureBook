package test.synctest;

import java.util.PriorityQueue;

/**
 * 使用Object.wait（）、Object.notify（）和非阻塞队列实现生产者-消费者模式
 */
public class ConsumerProducerTest {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    public static void main(String[] args) {
        ConsumerProducerTest test = new ConsumerProducerTest();
        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();
        consumer.start();
        producer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列空,等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //每次移走队首元素
                    queue.poll();
                    queue.notify();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满,等待空闲空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    //每次插入一个元素
                    queue.offer(1);
                    queue.notify();
                }
            }
        }
    }
}
