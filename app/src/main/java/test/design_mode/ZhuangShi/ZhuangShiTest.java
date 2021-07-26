package test.design_mode.ZhuangShi;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之装饰模式
 */
public class ZhuangShiTest {
    public static void main(String[] args) {
        YangGuo yang = new YangGuo();
        //洪七公向杨过教授打狗棒法
        HongQiGong hong = new HongQiGong(yang);
        hong.attachKungFu();
        //欧阳锋向杨过教授蛤蟆功
        OuYangFeng ou = new OuYangFeng(yang);
        ou.attachKungFu();
    }
}
