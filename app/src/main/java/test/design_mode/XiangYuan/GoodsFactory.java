package test.design_mode.XiangYuan;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ncx on 2021/6/11
 * 享元工厂
 */
public class GoodsFactory {
    private static Map<String,Goods> pool=new HashMap<>();

    public static Goods getGoods(String name) {
        if (pool.containsKey(name)) {
            System.out.println("使用缓存,Key为:" + name);
            return pool.get(name);
        } else {
            Goods goods = new Goods(name);
            pool.put(name,goods);
            System.out.println("创建商品,Key为:" + name);
            return goods;
        }
    }
}
