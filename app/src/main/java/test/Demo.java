package test;

import test.design_mode.SingletonEnum;

/**
 * Created by ncx on 2021/4/29
 */
public class Demo {
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.doSomething();
        System.out.println("This is demo");
    }
}
