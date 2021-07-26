package test.gcroottest;

/**
 * VM agrs: -Xms20M(初始化堆的大小20M) -Xmx20M(堆最大分配内存20M) -Xmn10M(新生代内存大小10M) -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 */
public class MinorGCTest {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];
        a4 = new byte[1 * _1MB];
//        a4 = new byte[2 * _1MB];
    }

    public static void main(String[] agrs) {
        testAllocation();
    }

    /**
     * 打印日志,可以看出堆内存总大小为 20M，其中新生代占 10M，剩下的 10M 会自动分配给老年代
     * 此时a1, a2, a3, a4都会分配在eden区
     * Heap
     * PSYoungGen      total 9216K, used 8003K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 8192K, 97% used [0x00000007bf600000,0x00000007bfdd0ed8,0x00000007bfe00000)
     *   from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
     *   to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
     * ParOldGen       total 10240K, used 0K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     *   object space 10240K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf600000)
     * Metaspace       used 2631K, capacity 4486K, committed 4864K, reserved 1056768K
     *   class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
     *
     *   如果a4 = new byte[1 * _1MB];改成 a4 = new byte[2 * _1MB];
     *   会打印如下日志,这是因为在给 a4 分配内存之前，Eden 区已经被占用 6M。已经无法再分配出 2M 来存储 a4 对象。
     *   因此会执行一次 Minor GC。并尝试将存活的 a1、a2、a3 复制到 S1 区。
     *   但是 S1 区只有 1M 空间，所以没有办法存储 a1、a2、a3 任意一个对象。
     *   在这种情况下 a1、a2、a3 将被转移到老年代，最后将 a4 保存在 Eden 区。
     *   所以最终结果就是：Eden 区占用 2M（a4），老年代占用 6M（a1、a2、a3）。
     *   [GC (Allocation Failure) [PSYoungGen: 6815K->480K(9216K)] 6815K->6632K(19456K), 0.0067344 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
     * Heap
     * PSYoungGen      total 9216K, used 2130K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 8192K, 26% used [0x00000007bf600000,0x00000007bf814930,0x00000007bfe00000)
     *   from space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
     *   to   space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
     * ParOldGen       total 10240K, used 6420K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
     *   object space 10240K, 62% used [0x00000007bec00000,0x00000007bf2450d0,0x00000007bf600000)
     * Metaspace       used 2632K, capacity 4486K, committed 4864K, reserved 1056768K
     *   class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
     */
}
