package test.design_mode.strategy;

/**
 * Created by ncx on 2021/6/11
 * 上下文角色
 */
public class Context {
    private FightingStrategy strategy;

    public Context(FightingStrategy strategy) {
        this.strategy = strategy;
    }

    public void fight() {
        strategy.fighting();
    }
}
