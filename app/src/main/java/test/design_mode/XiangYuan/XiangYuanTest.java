package test.design_mode.XiangYuan;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之享元模式
 */
public class XiangYuanTest {
    public static void main(String[] args) {
        Goods goods1 = GoodsFactory.getGoods("iphone7");
        goods1.showGoodsPrice("32G");
        Goods goods2 = GoodsFactory.getGoods("iphone7");
        goods2.showGoodsPrice("32G");
        Goods goods3 = GoodsFactory.getGoods("iphone7");
        goods3.showGoodsPrice("128G");
    }
}
