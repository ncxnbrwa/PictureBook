package com.example.picturebook.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ncx on 2021/6/15
 *
 */
@Module
public class EngineModule {

//    @Named("Gasoline") //Named注解用于区分具体子类
    @Provides
    @Gasoline  //这个效果和@Named是一样的
    public Engine provideGasoline() {
        return new GasolineEngine();
    }

//    @Named("Diesel")
    @Provides
    @Diesel
    public Engine provideDiesel() {
        return new DieselEngine();
    }
}
