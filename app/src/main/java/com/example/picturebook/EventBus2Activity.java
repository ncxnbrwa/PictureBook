package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.picturebook.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class EventBus2Activity extends AppCompatActivity {
    private TextView tvMsg;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);
        tvMsg = findViewById(R.id.tv_msg);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(v -> {
            //发送消息
            EventBus.getDefault().post(new MessageEvent("天下熙熙,皆为利来,天下攘攘,皆为利往"));
            //发送黏性消息
//            EventBus.getDefault().postSticky(new MessageEvent("黏性事件"));
            finish();
        });
    }
}