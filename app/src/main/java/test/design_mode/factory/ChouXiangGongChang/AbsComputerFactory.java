package test.design_mode.factory.ChouXiangGongChang;

import test.design_mode.factory.Computer;

/**
 * Created by ncx on 2021/6/11
 * 抽象工厂类
 */
public abstract class AbsComputerFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
