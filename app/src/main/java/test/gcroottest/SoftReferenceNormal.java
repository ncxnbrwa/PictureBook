package test.gcroottest;

import java.lang.ref.SoftReference;

/**
 * Created by ncx on 2021/3/16
 */
public class SoftReferenceNormal {
    static class SoftObject {
        byte[] data = new byte[120 * 1024 * 1024];
    }

    public static void main(String[] args) {
        SoftReference<SoftObject> cacheRef = new SoftReference<>(new SoftObject());
        System.out.println("第一次GC前 软引用:" + cacheRef.get());
        System.gc();
        System.out.println("第一次GC后 软引用:" + cacheRef.get());

        SoftObject newSoft = new SoftObject();
        System.out.println("再次分配120M的强引用对象之后 软引用:" + cacheRef.get());
    }
}
