package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentController;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.picturebook.service.BackgroundVideoService;
import com.example.picturebook.service.BackgroundVideoService2;

public class ServiceVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent, intent2;
//    private ServiceConnection sc = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_video);
        Button btnStartService = findViewById(R.id.btn_start_service);
        btnStartService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        intent = new Intent(this, BackgroundVideoService.class);
        intent2 = new Intent(this, BackgroundVideoService2.class);
//        bindService(intent, sc, Context.BIND_AUTO_CREATE);
        startService(intent);
        startService(intent2);
    }

    @Override
    protected void onDestroy() {
//        unbindService(sc);
        stopService(intent);
        stopService(intent2);
        super.onDestroy();
    }
}
