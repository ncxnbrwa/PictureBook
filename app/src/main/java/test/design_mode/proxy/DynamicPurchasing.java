package test.design_mode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ncx on 2021/6/11
 */
public class DynamicPurchasing implements InvocationHandler {
    private Object obj;

    public DynamicPurchasing(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = method.invoke(this.obj, args);
        if (method.getName().equals("buy")) {
            System.out.println("再买一些其他的东西");
        }
        return obj;
    }
}
