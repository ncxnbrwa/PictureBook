package test.design_mode.proxy;

/**
 * Created by ncx on 2021/6/11
 * 真是主题类
 */
public class People implements IShop{
    @Override
    public void buy() {
        System.out.println("买东西");
    }
}
