package com.example.picturebook.dagger;

import com.example.picturebook.StartActivity;

import dagger.Component;

/**
 * Created by ncx on 2021/6/15
 */
@Component(modules = WatchModule.class)
public interface StartActivityComponent {
    void inject(StartActivity activity);
}
