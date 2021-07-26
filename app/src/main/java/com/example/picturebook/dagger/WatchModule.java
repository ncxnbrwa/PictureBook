package com.example.picturebook.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ncx on 2021/6/15
 */
@Module
class WatchModule {

    @Provides
    public Watch provideWatch(){
        return new Watch();
    }
}
