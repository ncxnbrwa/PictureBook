package test.design_mode.template;

/**
 * Created by ncx on 2021/6/11
 */
public class ZhangSanFeng extends AbsSwordsMan{
    @Override
    protected void neigong() {
        System.out.println("运行纯阳无极功");
    }

    @Override
    protected void weapons() {
        System.out.println("使用真武剑");
    }

    @Override
    protected void move() {
        System.out.println("使用招式神门十三剑");
    }

    @Override
    protected void hook() {
        System.out.println("身体不适,老夫去趟厕所");
    }
}
