package test.design_mode.factory.JianDanGongChang;

import test.design_mode.factory.AsusComputer;
import test.design_mode.factory.Computer;
import test.design_mode.factory.HpComputer;
import test.design_mode.factory.LenovoComputer;

/**
 * Created by ncx on 2021/6/11
 */
public class ComputerFactory {
    public static Computer createComputer(String type) {
        Computer computer = null;
        switch (type) {
            case "lenovo":
                computer = new LenovoComputer();
                break;
            case "hp":
                computer = new HpComputer();
                break;
            case "asus":
                computer = new AsusComputer();
                break;
        }
        return computer;
    }
}
