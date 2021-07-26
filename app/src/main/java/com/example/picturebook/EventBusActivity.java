package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.picturebook.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMsg;
    private Button btnSubscript, btnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        tvMsg = findViewById(R.id.tv_msg);
        btnSubscript = findViewById(R.id.btn_subscript);
        btnIntent = findViewById(R.id.btn_intent);
        btnSubscript.setOnClickListener(this);
        btnIntent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_subscript:
                //注册事件
                EventBus.getDefault().register(EventBusActivity.this);
                break;
            case R.id.btn_intent:
                startActivity(new Intent(EventBusActivity.this,EventBus2Activity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        //接受消息的处理方法
        tvMsg.setText(messageEvent.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onStickyEvent(MessageEvent messageEvent) {
        //接受黏性事件
        //黏性事件在发送事件之后再订阅该事件也能收到该事件
        tvMsg.setText(messageEvent.getMessage());
    }
}