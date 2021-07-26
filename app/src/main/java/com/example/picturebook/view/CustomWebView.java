package com.example.picturebook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;

import com.example.picturebook.MyConfig;

/**
 * Created by ncx on 2020/5/16
 */
public class CustomWebView extends WebView {
    public CustomWebView(Context context) {
        this(context,null);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnKeyListener((v, keyCode, event) -> {
            Log.w(MyConfig.KEY_TAG, "CustomWebView setOnKeyListener:" + event.getKeyCode());
            return true;
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "CustomWebView dispatchKeyEvent:" + event.getKeyCode());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "CustomWebView dispatchKeyEvent:" + event.getKeyCode());
        return super.onKeyDown(keyCode, event);
    }
}
