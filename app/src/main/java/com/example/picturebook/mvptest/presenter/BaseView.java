package com.example.picturebook.mvptest.presenter;

/**
 * Created by ncx on 2021/6/15
 * 用于给View绑定Presenter
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}
