package test.design_mode;

/**
 * Created by ncx on 2021/6/11
 * 单例模式的枚举写法
 */
public enum SingletonEnum {
    INSTANCE;

    public void doSomething(){
        System.out.println("SingletonEnum doSomething");
    }
}
