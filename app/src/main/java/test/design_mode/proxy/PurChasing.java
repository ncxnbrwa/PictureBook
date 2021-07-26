package test.design_mode.proxy;

/**
 * Created by ncx on 2021/6/11
 * 代理类,有点像给真实的主题包了一层
 */
public class PurChasing implements IShop {
    private IShop iShop;

    public PurChasing(IShop iShop) {
        this.iShop = iShop;
    }

    @Override
    public void buy() {
        //在这就可以给代理的具体操作前后加些自己的处理
        System.out.println("-------------before buy-------------");
        iShop.buy();
        System.out.println("-------------after buy-------------");
    }
}
