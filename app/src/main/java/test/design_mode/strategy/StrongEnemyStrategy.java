package test.design_mode.strategy;

/**
 * Created by ncx on 2021/6/11
 * 强大对手策略
 */
public class StrongEnemyStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        System.out.println("你很勇哦,张无忌使用乾坤大挪移");
    }
}
