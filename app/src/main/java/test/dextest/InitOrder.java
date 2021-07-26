package test.dextest;

/**
 * Created by ncx on 2021/3/30
 */
public class InitOrder {
    public static void main(String[] args) {
        Parent p  = new Child();
        System.out.println("=====================");
        p = new Child();
    }

    /**
     * 对象的初始化顺序如下：
     * 静态变量/静态代码块 -> 普通代码块 -> 构造函数
     * 1. 父类静态变量和静态代码块；
     * 2. 子类静态变量和静态代码块；
     * 3. 父类普通成员变量和普通代码块；
     * 4. 父类的构造函数；
     * 5. 子类普通成员变量和普通代码块；
     * 6. 子类的构造函数。
     */

    static class Child extends Parent{
        static {
            System.out.println("Child static block!");
        }

        {
            System.out.println("Child non-static block!");
        }

        public Child(){
            System.out.println("Child constructor!");
        }
    }

    static class Parent{
        static {
            System.out.println("Parent static block!");
        }

        {
            System.out.println("Parent non-static block!");
        }

        public Parent(){
            System.out.println("Parent constructor!");
        }
    }
}
