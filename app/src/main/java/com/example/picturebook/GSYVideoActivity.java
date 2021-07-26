package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class GSYVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PictureBook_GSY";
    private StandardGSYVideoPlayer gsyVideoPlayer;
    private ImageButton btnPre, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsyvideo);
        gsyVideoPlayer = findViewById(R.id.gsy_video);
        btnBack = findViewById(R.id.btn_back);
        btnPre = findViewById(R.id.btn_pre);
        btnBack.setOnClickListener(this);
        btnPre.setOnClickListener(this);
        Debuger.enable();
        // 打开硬解码
        GSYVideoType.enableMediaCodec();
        //降低帧数,视频码率太高或在模拟器上会有音视频不同步的情况
        VideoOptionModel frameDropOption =
                new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 50);
//        VideoOptionModel seekAtStartOption =
//                new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "seek-at-start", startPosition);
//        VideoOptionModel videoOptionMode04 =
//                new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);//是否开启缓冲
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(frameDropOption);
//        list.add(videoOptionMode04);
        GSYVideoManager.instance().setOptionModelList(list);

        String testUrl = "http://vfx.mtime.cn/Video/2020/02/28/mp4/200228120933661630_1080.mp4";
        gsyVideoPlayer.setUp(testUrl, true, "时光网");
        gsyVideoPlayer.startPlayLogic();
        gsyVideoPlayer.setGSYVideoProgressListener((progress, secProgress, currentPosition, duration) -> {
            Log.d(TAG, "progress:" + progress + " secProgress:" + secProgress
                    + " currentPosition:" + currentPosition + " duration:" + duration);
//                Log.d(TAG, "setGSYVideoProgressListener duration:" + gsyVideoPlayer.getDuration());
        });
//        Log.d(TAG, "duration:" + gsyVideoPlayer.getDuration());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                gsyVideoPlayer.seekTo(gsyVideoPlayer.getCurrentPositionWhenPlaying() - 2000);
                break;
            case R.id.btn_pre:
                gsyVideoPlayer.seekTo(gsyVideoPlayer.getCurrentPositionWhenPlaying() + 2000);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        GSYVideoType.disableMediaCodec();
        GSYVideoManager.releaseAllVideos();
        super.onDestroy();
    }
}
