package test.design_mode.ZhuangShi;

/**
 * Created by ncx on 2021/6/11
 * 装饰者具体实现类
 */
public class OuYangFeng extends Master{
    public OuYangFeng(SwordsMan swordsMan) {
        super(swordsMan);
    }

    public void teachKungFu() {
        System.out.println("欧阳锋教授蛤蟆功");
        System.out.println("杨过学会蛤蟆功");
    }

    @Override
    public void attachKungFu() {
        super.attachKungFu();
        teachKungFu();
    }
}
