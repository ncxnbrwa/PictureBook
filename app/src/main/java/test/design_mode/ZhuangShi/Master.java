package test.design_mode.ZhuangShi;

/**
 * Created by ncx on 2021/6/11
 * 抽象装饰者
 */
public abstract class Master extends SwordsMan {
    private SwordsMan swordsMan;

    public Master(SwordsMan swordsMan) {
        this.swordsMan = swordsMan;
    }

    @Override
    public void attachKungFu() {
        swordsMan.attachKungFu();
    }
}
