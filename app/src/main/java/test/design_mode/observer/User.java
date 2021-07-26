package test.design_mode.observer;

/**
 * Created by ncx on 2021/6/11
 * 具体观察者
 */
public class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
