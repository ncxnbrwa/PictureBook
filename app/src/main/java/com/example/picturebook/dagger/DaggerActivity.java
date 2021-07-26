package com.example.picturebook.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.picturebook.BaseApp;
import com.example.picturebook.MyConfig;
import com.example.picturebook.R;

import javax.inject.Inject;

import dagger.Lazy;

public class DaggerActivity extends AppCompatActivity {
    @Inject
    Watch watch;
    @Inject
    Car car;
    @Inject
    Lazy<SwordsMan> swordsManLazy;//懒加载模式,使用的时候才会调用get方法来获取实例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
//        DaggerDaggerActivityComponent.create().inject(this);
        BaseApp.get(this).getDaggerActivityComponent().inject(this);
        watch.work();
        SwordsMan swordsMan = swordsManLazy.get();
        Log.d(MyConfig.DAGGER_TAG, "swordsMan.fighting:" + swordsMan.fighting());
        Log.d(MyConfig.DAGGER_TAG, "car.run:" + car.run());
    }
}