package test.design_mode.template;

/**
 * Created by ncx on 2021/6/11
 */
public class ZhangWuJi extends AbsSwordsMan{
    @Override
    protected void neigong() {
        System.out.println("运行九阳神功");
    }

    @Override
    protected void weapons() {

    }

    @Override
    protected void move() {
        System.out.println("使用招式乾坤大挪移");
    }

    @Override
    protected boolean hasWeapons() {
        return false;
    }
}
