package test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by ncx on 2021/4/26
 */
public class WeakrefDemo {
    public static void main(String[] args) throws InterruptedException {
//        WeakReference<BigObject> reference = new WeakReference<>(new BigObject());
//        System.out.println("gc前:" + reference.get());
//        System.gc();
//        Thread.sleep(1000);
//        System.out.println("gc后:" + reference.get());
        /**
         * gc前:test.WeakrefDemo$BigObject@135fbaa4
         * gc后:null
         */

        //当 BigObject 被回收之后，WeakReference 会被添加到所传入的 ReferenceQueue 中。
//        ReferenceQueue<BigObject> queue = new ReferenceQueue<>();
//        WeakReference<BigObject> reference = new WeakReference<>(new BigObject(),queue);
//        System.out.println("gc前 reference:" + reference.get());
//        System.out.println("gc前 queue:" + queue.poll());
//        System.gc();
//        Thread.sleep(1000);
//        System.out.println("gc后 reference:" + reference.get());
//        System.out.println("gc后 queue:" + queue.poll());
        /**
         * gc前 reference:test.WeakrefDemo$BigObject@135fbaa4
         * gc前 queue:null
         * gc后 reference:null
         * gc后 queue:java.lang.ref.WeakReference@45ee12a7
         */

        //bigObject是个强引用,所以GC不会回收
        ReferenceQueue<BigObject> queue = new ReferenceQueue<>();
        BigObject bigObject = new BigObject();
        WeakReference<BigObject> reference = new WeakReference<>(bigObject, queue);
        System.out.println("gc前 reference:" + reference.get());
        System.out.println("gc前 queue:" + queue.poll());
        System.gc();
        Thread.sleep(1000);
        System.out.println("gc后 reference:" + reference.get());
        System.out.println("gc后 queue:" + queue.poll());
        /**
         * gc前 reference:test.WeakrefDemo$BigObject@135fbaa4
         * gc前 queue:null
         * gc后 reference:test.WeakrefDemo$BigObject@135fbaa4
         * gc后 queue:null
         */
    }

    static class BigObject {
    }
}
