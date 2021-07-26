package test.design_mode.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ncx on 2021/6/11
 */
public class SubscriptionSubject implements Subject {
    private List<Observer> userList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        userList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        userList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : userList) {
            observer.update(message);
        }
    }
}
