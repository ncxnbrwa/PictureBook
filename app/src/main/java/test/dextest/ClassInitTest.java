package test.dextest;

/**
 * Created by ncx on 2021/3/30
 */
public class ClassInitTest {
    public static void main(String[] args) {
        ClassInit.value = 2; //调用这句代码ClassInit的static代码块会执行
        ClassInit ci = new ClassInit();//调用这句代码ClassInit的非静态代码块会执行
    }
}
