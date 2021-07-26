package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private List<ImageView> list = new ArrayList<>();
    static MemoryLeak memoryLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
        btn = findViewById(R.id.btn_test);
        btn.setOnClickListener(this);
        memoryLeak = new MemoryLeak();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                for (int i = 0; i < 10000; i++) {
                    ImageView imageView = new ImageView(this);
                    list.add(imageView);
                }
                break;
        }
    }

    class MemoryLeak{
        void doSomething() {
            System.out.println("Wheee!!!");
        }
    }
}