package com.example.picturebook.rx;

/**
 * Created by ncx on 2021/6/13
 */
public class Emperor {
    private String dynasty; //朝代
    private String name;

    public Emperor() {
    }

    public Emperor(String dynasty, String name) {
        this.dynasty = dynasty;
        this.name = name;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emperor{" +
                "dynasty='" + dynasty + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
