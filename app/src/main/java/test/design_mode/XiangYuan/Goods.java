package test.design_mode.XiangYuan;

/**
 * Created by ncx on 2021/6/11
 * 具体享元角色
 */
public class Goods implements IGoods{
    //内部状态
    private String name;
    //外部状态
    private String version;

    Goods(String name){
        this.name = name;
    }
    @Override
    public void showGoodsPrice(String version) {
        if (version.equals("32G")) {
            System.out.println("价格为5199元");
        } else if (version.equals("128G")) {
            System.out.println("价格为5999元");
        }
    }
}
