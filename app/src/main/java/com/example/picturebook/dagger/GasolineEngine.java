package com.example.picturebook.dagger;

import javax.inject.Inject;

/**
 * Created by ncx on 2021/6/15
 */
public class GasolineEngine extends Engine{

    @Override
    public String work() {
        return "汽车发动机发动";
    }
}
