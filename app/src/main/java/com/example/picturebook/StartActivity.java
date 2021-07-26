package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.picturebook.dagger.DaggerActivity;
import com.example.picturebook.dagger.DaggerStartActivityComponent;
import com.example.picturebook.dagger.Watch;
import com.example.picturebook.rx.RxActivity;
import com.example.picturebook.utils.ScreenUtils;
import com.example.picturebook.view.CustomViewHomeActivity;

import javax.inject.Inject;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "StartActivity";
    private Button btnSystemVideo, btnSystemAudio, btnGsyVideo, btnWeb, btnMultiVideo,
            btnListGSYVideo, btnMultiGSYVideo, btnServiceVideo, btnAppInfo, btnRecyclerView, btnConstraint,
            btnBitmap, btnMemoryLeak, btnCustomView, btnEventBus, btnRxjava, btnDagger;

    @Inject
    Watch watch;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DaggerStartActivityComponent.create().inject(this);
        watch.work();

        ClassLoader classLoader = StartActivity.class.getClassLoader();
        while (classLoader != null) {
            Log.e("ncx", classLoader.toString());
            classLoader = classLoader.getParent();
        }

        btnGsyVideo = findViewById(R.id.btn_gsy_video);
        btnSystemVideo = findViewById(R.id.btn_system_video);
        btnSystemAudio = findViewById(R.id.btn_system_audio);
        btnWeb = findViewById(R.id.btn_web);
        btnMultiVideo = findViewById(R.id.btn_multi_video);
        btnMultiGSYVideo = findViewById(R.id.btn_multi_gsyvideo);
        btnListGSYVideo = findViewById(R.id.btn_list_gsyvideo);
        btnServiceVideo = findViewById(R.id.btn_service_video);
        btnAppInfo = findViewById(R.id.btn_appinfo);
        btnRecyclerView = findViewById(R.id.btn_recyclerview);
        btnConstraint = findViewById(R.id.btn_constraintLayout);
        btnBitmap = findViewById(R.id.btn_bitmap);
        btnMemoryLeak = findViewById(R.id.btn_memory_leak);
        btnCustomView = findViewById(R.id.btn_custom_view);
        btnEventBus = findViewById(R.id.btn_event_bus);
        btnRxjava = findViewById(R.id.btn_rxjava);
        btnDagger = findViewById(R.id.btn_dagger);
        btnGsyVideo.setOnClickListener(this);
        btnSystemAudio.setOnClickListener(this);
        btnSystemVideo.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnMultiVideo.setOnClickListener(this);
        btnMultiGSYVideo.setOnClickListener(this);
        btnListGSYVideo.setOnClickListener(this);
        btnServiceVideo.setOnClickListener(this);
        btnAppInfo.setOnClickListener(this);
        btnRecyclerView.setOnClickListener(this);
        btnConstraint.setOnClickListener(this);
        btnBitmap.setOnClickListener(this);
        btnMemoryLeak.setOnClickListener(this);
        btnCustomView.setOnClickListener(this);
        btnEventBus.setOnClickListener(this);
        btnRxjava.setOnClickListener(this);
        btnDagger.setOnClickListener(this);
        btnMultiGSYVideo.requestFocus();
        requestPermission();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_system_video:
                startActivity(new Intent(this, SystemVideoActivity.class));
                break;
            case R.id.btn_gsy_video:
                startActivity(new Intent(this, GSYVideoActivity.class));
                break;
            case R.id.btn_system_audio:
                startActivity(new Intent(this, SystemAudioActivity.class));
                break;
            case R.id.btn_web:
                startActivity(new Intent(this, WebActivity.class));
                break;
            case R.id.btn_multi_video:
                startActivity(new Intent(this, MultiVideoActivity.class));
                break;
            case R.id.btn_list_gsyvideo:
                startActivity(new Intent(this, ListMultiVideoActivity.class));
                break;
            case R.id.btn_multi_gsyvideo:
                startActivity(new Intent(this, MultiGSYVideoActivity.class));
                break;
            case R.id.btn_service_video:
                startActivity(new Intent(this, ServiceVideoActivity.class));
                break;
            case R.id.btn_appinfo:
                startActivity(new Intent(this, AppInfoActivity.class));
                break;
            case R.id.btn_recyclerview:
                startActivity(new Intent(this, TestRecyclerviewActivity.class));
                break;
            case R.id.btn_constraintLayout:
                startActivity(new Intent(this, ConstraintLayoutActivity.class));
                break;
            case R.id.btn_bitmap:
                startActivity(new Intent(this, BitmapActivity.class));
                break;
            case R.id.btn_memory_leak:
                startActivity(new Intent(this, MemoryLeakActivity.class));
                break;
            case R.id.btn_custom_view:
                startActivity(new Intent(this, CustomViewHomeActivity.class));
                break;
            case R.id.btn_event_bus:
                startActivity(new Intent(this, EventBusActivity.class));
                break;
            case R.id.btn_rxjava:
                startActivity(new Intent(this, RxActivity.class));
                break;
            case R.id.btn_dagger:
                startActivity(new Intent(this, DaggerActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        MutableLiveData<Integer> liveData = new MutableLiveData<>();
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private final int REQUEST_CODE = 0x123;

    public void requestPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_CONTACTS)) {
//                    Log.d(TAG, "上次用户拒绝了WRITE_CONTACTS权限");
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS},
//                            REQUEST_CODE);
//                } else {
//                    Log.d(TAG, "用户永久拒绝了WRITE_CONTACTS权限");
//                }
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS},
                        REQUEST_CODE);
            } else {
                Log.d(TAG, "已申请WRITE_CONTACTS权限");
            }
        }
    }
}


