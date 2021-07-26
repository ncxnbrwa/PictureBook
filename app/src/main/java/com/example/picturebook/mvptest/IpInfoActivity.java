package com.example.picturebook.mvptest;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.picturebook.R;
import com.example.picturebook.mvptest.model.IpInfoTask;
import com.example.picturebook.mvptest.presenter.IpInfoPresenter;

/**
 * Created by ncx on 2021/6/15
 * 这个例子中IpInfoActivity并不作为View层，而是作为View、Model和Presenter三层的纽带
 */
public class IpInfoActivity extends AppCompatActivity {
    private IpInfoPresenter ipInfoPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipinfo);
        IpInfoFragment ipInfoFragment = (IpInfoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (ipInfoFragment == null) {
            ipInfoFragment = IpInfoFragment.newInstance();
            //fragment添加到当前Activity中
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager()
                    , ipInfoFragment, R.id.contentFrame);
        }
        IpInfoTask ipInfoTask = IpInfoTask.getInstance();
        ipInfoPresenter = new IpInfoPresenter(ipInfoTask, ipInfoFragment);
        //设置Presenter
        ipInfoFragment.setPresenter(ipInfoPresenter);
    }
}
