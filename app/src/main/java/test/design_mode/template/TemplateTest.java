package test.design_mode.template;

/**
 * Created by ncx on 2021/6/11
 */
public class TemplateTest {
    public static void main(String[] args) {
        ZhangWuJi wuJi = new ZhangWuJi();
        wuJi.fighting();
        ZhangSanFeng sanFeng = new ZhangSanFeng();
        sanFeng.fighting();
    }
}
