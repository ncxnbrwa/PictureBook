package test.design_mode.factory.ChouXiangGongChang;

import test.design_mode.factory.AsusComputer;
import test.design_mode.factory.HpComputer;
import test.design_mode.factory.LenovoComputer;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之抽象工厂方法的使用
 */
public class AbsFactoryTest {
    public static void main(String[] args) {
        AbsComputerFactory factory  = new GDComputerFactory();
        LenovoComputer lenovoComputer = factory.createComputer(LenovoComputer.class);
        lenovoComputer.start();
        AsusComputer asusComputer = factory.createComputer(AsusComputer.class);
        asusComputer.start();
        HpComputer hpComputer = factory.createComputer(HpComputer.class);
        hpComputer.start();
    }
}
