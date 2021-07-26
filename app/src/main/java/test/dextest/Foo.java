package test.dextest;

import java.util.ArrayList;

public class Foo {
    public static void main(String[] args) {
        new Foo().print();
        ArrayList<Integer> list = new ArrayList<>();
        
    }

    public void print() {
        int superCode = super.hashCode();
        System.out.println("superCode is " + superCode);

        int thisCode = hashCode();
        System.out.println("thisCode is " + thisCode);
    }

    public int hashCode() {
        return 111;
    }
}
