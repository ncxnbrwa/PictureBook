package test.dextest;

import java.nio.file.Paths;

/**
 * Created by ncx on 2021/3/30
 */
public class Parent {
    public static int value = 1;
    static {
        System.out.println("this is Parent!");
    }

}

class Child extends Parent{
    static {
        System.out.println("this is Child!");
    }
}
