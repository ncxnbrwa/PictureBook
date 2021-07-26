package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.picturebook.view.CustomWebView;

public class WebActivity extends AppCompatActivity {
    private CustomWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = findViewById(R.id.web);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        mWebView.loadUrl("https://www.baidu.com/");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "WebActivity dispatchKeyEvent:" + event.getKeyCode());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "WebActivity onKeyDown:" + event.getKeyCode());
        return super.onKeyDown(keyCode, event);
    }

}
