package com.example.picturebook.view.custom_study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.picturebook.R;
import com.example.picturebook.view.custom_study.customview.ControlView;

public class ControlViewActivity extends AppCompatActivity {
    private ControlView controlView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view);
        controlView = findViewById(R.id.control_view);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            //使用属性动画使view滑动
//            ObjectAnimator.ofFloat(controlView, "translationX", 0, 300).setDuration(1000).start();
            //使用Scroll来进行平滑移动
            controlView.smoothScrollTo(-200, 50);
        });
    }
}