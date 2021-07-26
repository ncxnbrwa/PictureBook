package test.design_mode.strategy;

/**
 * Created by ncx on 2021/6/11
 * 弱的敌人的策略
 */
public class WeakEnemyStrategy implements FightingStrategy{
    @Override
    public void fighting() {
        System.out.println("拜托,你很弱诶,张无忌使用太极剑");
    }
}
