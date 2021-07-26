package com.example.picturebook.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ncx on 2021/6/15
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Diesel {
}
