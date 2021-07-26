package test.classloadtest;

/**
 * Created by ncx on 2021/3/26
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader c1 = ClassLoaderTest.class.getClassLoader();
        System.out.println("c1 is " + c1);
        ClassLoader parent = c1.getParent();
        System.out.println("parent is " + parent);
        ClassLoader boot_strap = parent.getParent();
        System.out.println("boot_strap is " + boot_strap);
    }

}
