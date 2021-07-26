package test.gcroottest;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ncx on 2021/3/16
 */
public class SoftReferenceTest {
    public static class SoftObject {
        byte[] data = new byte[1024];
    }

    public static int removeSoftRefs = 0;
    public static int CACHE_INITIAL_CAPACITY = 100 * 1024;
    public static Set<SoftReference<SoftObject>> cache = new HashSet<>();
    //用一个引用队列管理软引用,必要的时候释放资源
    public static ReferenceQueue<SoftObject> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) {
        for (int i = 0; i < CACHE_INITIAL_CAPACITY; i++) {
            SoftObject obj = new SoftObject();
            cache.add(new SoftReference<>(obj));
            clearUselessReferences();
            if (i % 1000 == 0) {
                System.out.println("size of cache:" + cache.size());
            }
        }
        System.out.println("结束");
    }

    public static void clearUselessReferences() {
        Reference<? extends SoftObject> ref = referenceQueue.poll();
        while (ref != null) {
            if (cache.remove(ref)) {
                removeSoftRefs++;
            }
            ref = referenceQueue.poll();
        }
    }
}
