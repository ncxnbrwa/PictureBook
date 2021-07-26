package com.example.picturebook.dagger;

import android.util.Log;

import com.example.picturebook.MyConfig;

import javax.inject.Inject;

/**
 * Created by ncx on 2021/6/15
 */
public class Watch {

    public void work() {
        Log.d(MyConfig.DAGGER_TAG, "手表工作");
    }
}
