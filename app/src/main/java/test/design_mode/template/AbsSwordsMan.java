package test.design_mode.template;

/**
 * Created by ncx on 2021/6/11
 * 抽象算法类
 */
public abstract class AbsSwordsMan {
    public final void fighting() {
        neigong();
        meridian();
        if (hasWeapons()) {
            weapons();
        }
        move();
        hook();
    }

    //空实现方法
    protected void hook() {
    }

    protected abstract void neigong();

    protected abstract void weapons();

    protected abstract void move();

    protected void meridian() {
        System.out.println("打通经脉");
    }

    protected boolean hasWeapons() {
        return true;
    }
}
