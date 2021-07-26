package com.example.picturebook;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.transition.Explode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.picturebook.adapter.ListMultiNormalAdapter;
import com.example.picturebook.manager.CustomManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 多个同时播放的demo
 */
public class ListMultiVideoActivity extends AppCompatActivity {
    private static final String TAG = "ListMultiVideoActivity";

    ListView videoList;

    ListMultiNormalAdapter listMultiNormalAdapter;

    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置一个exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        videoList = findViewById(R.id.video_list);

        //切换播放器内核
//        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
//        PlayerFactory.setPlayManager(SystemPlayerManager.class);
        listMultiNormalAdapter = new ListMultiNormalAdapter(this);
        videoList.setAdapter(listMultiNormalAdapter);

        videoList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }

        });


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Log.d(TAG, "计时器呢!!!!!!!!!");
//                if (videoList != null) {
//                    for (int i = 0; i < videoList.getChildCount(); i++) {
//                        pretendClick(videoList.getChildAt(i));
//                    }
//                }
//            }
//        }, 10000, 10000);
    }

    @Override
    public void onBackPressed() {
        //如果有视频全屏就先退出全屏
        if (CustomManager.backFromWindowFull(this, listMultiNormalAdapter.getFullKey())) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        CustomManager.onPauseAll();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        CustomManager.onResumeAll();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomManager.clearAllVideo();
    }

}
