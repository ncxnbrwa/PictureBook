package com.example.picturebook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.VideoView;

import com.example.picturebook.MyConfig;

/**
 * Created by ncx on 2019/10/24
 */
public class CustomVideoView extends VideoView {
    int defaultWidth = 1920;
    int defaultHeight = 1080;

    public CustomVideoView(Context context) {
        this(context, null);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setOnKeyListener((v, keyCode, event) -> {
//            Log.w(MyConfig.KEY_TAG, "CustomVideoView setOnKeyListener:" + event.getKeyCode());
//            return false;
//        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置默认宽高,重新绘制时能保证铺满屏幕
        int width = getDefaultSize(defaultWidth, widthMeasureSpec);
        int height = getDefaultSize(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "CustomVideoView dispatchKeyEvent:" + event.getKeyCode());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "CustomVideoView onKeyDown:" + event.getKeyCode());
        return false;
    }
}
