package test.design_mode.observer;

/**
 * Created by ncx on 2021/6/11
 * 抽象被观察者,例如公众号
 */
public interface Subject {
    //增加订阅者
    public void attach(Observer observer);

    //删除订阅者
    public void detach(Observer observer);

    //通知订阅者有更新
    public void notify(String message);
}
