package com.example.picturebook.view.custom_study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.picturebook.R;
import com.example.picturebook.view.custom_study.customview.TitleBar;

public class TestViewActivity extends AppCompatActivity {
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        titleBar = findViewById(R.id.title);
        titleBar.setLeftListener(v -> finish());
        titleBar.setRightListener(v -> Toast.makeText(TestViewActivity.this, "点击右键", Toast.LENGTH_SHORT).show());
    }
}