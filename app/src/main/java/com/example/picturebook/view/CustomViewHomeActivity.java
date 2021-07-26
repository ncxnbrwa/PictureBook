package com.example.picturebook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.picturebook.R;
import com.example.picturebook.view.custom_study.CanvasOperationActivity;
import com.example.picturebook.view.custom_study.ControlViewActivity;
import com.example.picturebook.view.custom_study.TestViewActivity;
import com.example.picturebook.view.custom_study.customview.HorizontalViewActivity;

public class CustomViewHomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLayerAlpha, btnControlView, btnInvalidText, btnHorizontalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {
        btnLayerAlpha = findViewById(R.id.btn_layer_alpha);
        btnControlView = findViewById(R.id.btn_control_view);
        btnInvalidText = findViewById(R.id.btn_invalid_text);
        btnHorizontalView = findViewById(R.id.btn_horizontal_view);
        btnLayerAlpha.setOnClickListener(this);
        btnControlView.setOnClickListener(this);
        btnInvalidText.setOnClickListener(this);
        btnHorizontalView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_layer_alpha:
                startActivity(new Intent(this, CanvasOperationActivity.class));
                break;
            case R.id.btn_control_view:
                startActivity(new Intent(this, ControlViewActivity.class));
                break;
            case R.id.btn_invalid_text:
                startActivity(new Intent(this, TestViewActivity.class));
                break;
            case R.id.btn_horizontal_view:
                startActivity(new Intent(this, HorizontalViewActivity.class));
                break;
        }
    }
}