package test.design_mode.factory.JianDanGongChang;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之简单工厂的使用
 */
public class CreateComputerTest {
    public static void main(String[] args) {
//        ComputerFactory.createComputer("asus").start();
//        ComputerFactory.createComputer("hp").start();
        ComputerFactory.createComputer("lenovo").start();
    }
}
