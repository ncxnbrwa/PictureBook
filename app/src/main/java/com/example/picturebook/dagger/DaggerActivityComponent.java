package com.example.picturebook.dagger;

import dagger.Component;

/**
 * Created by ncx on 2021/6/15
 */
@Component(modules = {EngineModule.class, WatchModule.class}
        , dependencies = {SwordsmanComponent.class})
public interface DaggerActivityComponent {
    void inject(DaggerActivity activity);
}