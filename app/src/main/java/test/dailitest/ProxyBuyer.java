package test.dailitest;

/**
 * Created by ncx on 2021/3/24
 * 代理类
 */
class ProxyBuyer implements Subject {
    People people;

    public ProxyBuyer() {
    }

    @Override
    public void buyAJ() {
        if (people == null) {
            people = new People();
        }
        people.buyAJ();
        System.out.println("代购了一双AJ");
    }
}
