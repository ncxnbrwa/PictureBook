package test.design_mode.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之代理模式
 */
public class ProxyTest {
    public static void main(String[] args) {
        //静态代理
        IShop people = new People();
//        IShop purchasing = new PurChasing(people);
//        purchasing.buy();
        //动态代理
        DynamicPurchasing dynamicPurchasing = new DynamicPurchasing(people);
        IShop purchasing = (IShop) Proxy.newProxyInstance(people.getClass().getClassLoader(), new Class[]{IShop.class},
                dynamicPurchasing);
        purchasing.buy();
    }
}
