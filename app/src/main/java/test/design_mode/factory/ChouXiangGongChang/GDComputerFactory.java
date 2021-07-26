package test.design_mode.factory.ChouXiangGongChang;

import test.design_mode.factory.Computer;

/**
 * Created by ncx on 2021/6/11
 * 具体工厂类
 */
public class GDComputerFactory extends AbsComputerFactory{
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String name = clz.getName();
        try {
            //通过反射来生成不同厂家的计算机
            computer = (Computer) Class.forName(name).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
