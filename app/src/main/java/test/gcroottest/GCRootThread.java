package test.gcroottest;

/**
 * Created by ncx on 2021/3/16
 */
public class GCRootThread {

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) throws Exception {
        System.out.println("开始前内存情况:");
        printMemory();
        AsyncTask at = new AsyncTask(new GCRootThread());
        Thread thread = new Thread(at);
        thread.start();
        //此时线程开启,且没有结束,它会作为GCRoot对象,GC无法回收改内存
        System.gc();
        System.out.println("main方法执行完毕，完成GC");
        printMemory();

        thread.join();// 保证线程结束再调用后续代码
        at = null;
        System.gc();
        System.out.println("线程代码执行完毕，完成GC");
        printMemory();
    }

    /**
     * 打印出当前JVM剩余空间和总的空间大小
     */
    public static void printMemory() {
        System.out.print("free is " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + " M, ");
        System.out.println("total is " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + " M, ");
    }

    private static class AsyncTask implements Runnable {
        private GCRootThread gcRootThread;

        public AsyncTask(GCRootThread gcRootThread) {
            this.gcRootThread = gcRootThread;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 运行结果:
     * 开始前内存情况:
     * free is 240 M, total is 245 M,
     * main方法执行完毕，完成GC
     * free is 163 M, total is 245 M,
     * 线程代码执行完毕，完成GC
     * free is 243 M, total is 245 M,
     */
}
