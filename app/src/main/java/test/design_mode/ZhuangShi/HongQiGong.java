package test.design_mode.ZhuangShi;

/**
 * Created by ncx on 2021/6/11
 * 装饰者具体实现类
 */
public class HongQiGong extends Master{
    public HongQiGong(SwordsMan swordsMan) {
        super(swordsMan);
    }

    public void teachKungFu() {
        System.out.println("洪七公教授打狗棒法");
        System.out.println("杨过学会打狗棒法");
    }

    @Override
    public void attachKungFu() {
        super.attachKungFu();
        teachKungFu();
    }
}
