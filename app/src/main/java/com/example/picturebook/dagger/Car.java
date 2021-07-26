package com.example.picturebook.dagger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ncx on 2021/6/15
 */
public class Car {
    private Engine engine;

    @Inject
//    public Car(@Named("Diesel") Engine engine) {
    public Car(@Diesel Engine engine) {
        this.engine = engine;
    }

    public String run() {
        return engine.work();
    }
}
