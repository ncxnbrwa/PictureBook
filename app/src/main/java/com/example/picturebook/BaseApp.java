package com.example.picturebook;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.picturebook.dagger.DaggerActivityComponent;
import com.example.picturebook.dagger.DaggerDaggerActivityComponent;
import com.example.picturebook.dagger.DaggerSwordsmanComponent;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by ncx on 2021/4/26
 */
public class BaseApp extends Application {
    DaggerActivityComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseExceptionHandler.getInstance().init(this);
        component = DaggerDaggerActivityComponent.builder()
                .swordsmanComponent(DaggerSwordsmanComponent.builder().build())
                .build();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }

    public static BaseApp get(Context context) {
        return (BaseApp) context.getApplicationContext();
    }

    public DaggerActivityComponent getDaggerActivityComponent() {
        return component;
    }
}
