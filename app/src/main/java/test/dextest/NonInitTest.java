package test.dextest;

/**
 * Created by ncx on 2021/3/30
 */
public class NonInitTest {
    public static void main(String[] args) {
        Child.value = 2;//此时只会执行父类Parent的静态代码块,不会执行Child的静态代码块
        //对于静态字段，只有直接定义这个字段的类才会被初始化
    }
}
