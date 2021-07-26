package test.classloadtest

import org.junit.Test

import java.lang.reflect.Method

/**
 * Created by ncx on 2021/3/26
 */
class DiskClassLoaderTest {
    @Test
    public void testClassLoader() {
        //创建自定义ClassLoader对象,这个路径我还不太明白是怎么样的
//        DiskClassLoader diskClassLoader = new DiskClassLoader("file:///Users");
        DiskClassLoader diskClassLoader = new DiskClassLoader("E:/MyTestDemo/PictureBook/app/src/main/java/test/classloadtest");
        //E:/MyTestDemo/PictureBook/app/src/main/java/test/classloadtest
        try {
            Class c = diskClassLoader.loadClass("Secret");
            if (c != null) {
                Object obj = c.newInstance();
                //反射调用Secret的printSecret方法
                Method method = c.getDeclaredMethod("printSecret", null);
                method.invoke(obj, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
