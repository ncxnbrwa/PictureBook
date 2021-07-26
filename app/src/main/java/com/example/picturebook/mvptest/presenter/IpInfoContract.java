package com.example.picturebook.mvptest.presenter;

import com.example.picturebook.mvptest.model.IpInfo;

/**
 * Created by ncx on 2021/6/15
 */
public interface IpInfoContract {
    interface Presenter{
        void getIpInfo(String ip);
    }

    interface View extends BaseView<Presenter>{
        void setIpInfo(IpInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        //判断Fragment是否添加到了Activity中
        boolean isActive();
    }
}
