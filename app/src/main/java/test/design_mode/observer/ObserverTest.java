package test.design_mode.observer;

/**
 * Created by ncx on 2021/6/11
 * 设计模式之观察者模式
 */
public class ObserverTest {
    public static void main(String[] args) {
        SubscriptionSubject subject = new SubscriptionSubject();
        //创建用户
        User user1 = new User("汉高祖");
        User user2 = new User("汉文帝");
        User user3 = new User("汉武帝");
        subject.attach(user1);
        subject.attach(user2);
        subject.attach(user3);
        subject.notify("秦始皇的帝国专栏更新了");
    }
}
