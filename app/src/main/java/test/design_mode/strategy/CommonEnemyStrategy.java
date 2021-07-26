package test.design_mode.strategy;

/**
 * Created by ncx on 2021/6/11
 * 普通对手策略
 */
public class CommonEnemyStrategy implements  FightingStrategy{
    @Override
    public void fighting() {
        System.out.println("普通对手,张无忌使用圣火令神功");
    }
}
