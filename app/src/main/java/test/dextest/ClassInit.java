package test.dextest;

/**
 * Created by ncx on 2021/3/30
 */
public class ClassInit {
    public static int value = 1;

    //静态语句块在初始化阶段执行
    static {
        System.out.println("classInit static block!");
    }

    //非静态语句块只在创建对象实例时执行
    {
        System.out.println("classInit non-static block!");
    }
}
