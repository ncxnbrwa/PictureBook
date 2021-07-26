package com.example.picturebook.mvptest.model;


import com.example.picturebook.mvptest.LoadTasksCallBack;

/**
 * Created by ncx on 2021/6/15
 */

public interface NetTask<T> {
    void execute(T data , LoadTasksCallBack callBack);
}
