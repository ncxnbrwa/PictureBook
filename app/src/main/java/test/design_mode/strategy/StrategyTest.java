package test.design_mode.strategy;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之策略模式
 */
public class StrategyTest {
    public static void main(String[] args) {
        Context context;
        context = new Context(new WeakEnemyStrategy());
        context.fight();
        context = new Context(new CommonEnemyStrategy());
        context.fight();
        context = new Context(new StrongEnemyStrategy());
        context.fight();
    }
}
