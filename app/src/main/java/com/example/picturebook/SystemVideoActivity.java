package com.example.picturebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picturebook.view.CustomVideoView;
import com.example.picturebook.utils.ScreenUtils;

public class SystemVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PictureBook_Main";
    private static final int UPDATE_UI = 0x123;

    private CustomVideoView mVideoView;
    private MediaPlayer mediaPlayer;
    private FrameLayout rootLayout;
    private RelativeLayout choiceRootLayout;
    private ImageView ivChoice1, ivChoice2, ivChoice3, ivBall;
    private WebView mWebView;
    private TextView tvVideoTime;
    private LinearLayout choiceLayout;
    private ImageButton btnBack, btnPre;
    private ProgressBar mProgressBar;
    private static final int REQUEST_CODE = 0x123;
    private int screenWidth, screenHeight;
    //管理器
    private AudioManager mAudioManager;
    //全屏标记
    private boolean isFullScreen;
    private boolean isComplete;
    private float brightness;
    private Point currentPoint;
    private AnimationDrawable anim;
    //正确答案下标,从1开始
    private int correctIndex = 1;
    //选择是否做完的标志
    private boolean isOver = false;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_UI) {
                if (mProgressBar != null && tvVideoTime != null && mVideoView != null) {
//                    int duration = mVideoView.getDuration();
                    int duration = mediaPlayer.getDuration();
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    Log.d(TAG, "getCurrentPosition:" + currentPosition + " getDuration:" + duration);
                    tvVideoTime.setText(String.format("%d/%d", currentPosition / 1000, duration / 1000));
                    mProgressBar.setProgress(currentPosition * 100 / duration);
                    mProgressBar.setSecondaryProgress(mVideoView.getBufferPercentage());
                    //如果播放完了就不再获取进度了
                    if (!isComplete) {
                        mHandler.sendEmptyMessageDelayed(UPDATE_UI, 500);
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video);
        //让整个应用变灰
//        Paint mPaint = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setSaturation(0);
//        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
//        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, mPaint);

        initUI();
//        mWebView = new WebView(this) {
//            @Override
//            public boolean dispatchKeyEvent(KeyEvent event) {
//                Log.w(MyConfig.KEY_TAG, "mWebView dispatchKeyEvent:" + event.getKeyCode());
//                return super.dispatchKeyEvent(event);
//            }
//
//            @Override
//            public boolean onKeyDown(int keyCode, KeyEvent event) {
//                Log.w(MyConfig.KEY_TAG, "mWebView onKeyDown:" + event.getKeyCode());
//                return super.onKeyDown(keyCode, event);
//            }
//        };
//        rootLayout.addView(mWebView, 0);
//        mWebView.requestFocus();
//        mWebView.loadUrl("https://www.baidu.com/");
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            } else {
                configVideoView(R.raw.mov_start, false);
//                configVideoView(1, false);
//                switchChoiceView(true);
            }
        } else {
            configVideoView(R.raw.mov_start, false);
//            configVideoView(1, false);
//            switchChoiceView(true);
        }
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initUI() {
        mVideoView = findViewById(R.id.video_view);
        choiceRootLayout = findViewById(R.id.choice_question_root);
        ivChoice1 = findViewById(R.id.iv_choice1);
        ivChoice2 = findViewById(R.id.iv_choice2);
        ivChoice3 = findViewById(R.id.iv_choice3);
        choiceLayout = findViewById(R.id.ll_house);
        mProgressBar = findViewById(R.id.progress);
        tvVideoTime = findViewById(R.id.tv_video_time);
        ivBall = findViewById(R.id.iv_ball);
        btnBack = findViewById(R.id.btn_back);
        btnPre = findViewById(R.id.btn_pre);
        rootLayout = findViewById(R.id.root_layout);
        ivChoice1.setOnClickListener(this);
        ivChoice2.setOnClickListener(this);
        ivChoice3.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnPre.setOnClickListener(this);
    }

    private void setVideoParam(int width, int height) {
        ViewGroup.LayoutParams lp = mVideoView.getLayoutParams();
        lp.width = width;
        lp.height = height;
        mVideoView.setLayoutParams(lp);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "SystemVideoActivity dispatchKeyEvent:" + event.getKeyCode());
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.w(MyConfig.KEY_TAG, "SystemVideoActivity onKeyDown:" + event.getKeyCode());
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configVideoView(R.raw.mov_start, false);
//                switchChoiceView(true);
            } else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void configVideoView(final int rawRes, final boolean isResultVideo) {
        mVideoView.setVisibility(View.VISIBLE);
        choiceRootLayout.setVisibility(View.GONE);
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mov_start.mp4";
        //播放本地视频
//        mVideoView.setVideoPath(path);
        //网络播放
        final String path = "http://vfx.mtime.cn/Video/2020/06/23/mp4/200623113742928637.mp4";
//        final String path = "http://183.207.248.71:80/haochuan/vod/pjsag001401000000000000000000573/mjsag001401000000000000000000573?OTTUserToken=reg00140000003-4C4576000312&UserName=reg00140000003&accountinfo=1kUWEHEoCFiR%2Bm%2BFn%2FmvKDKh%2BDzCeq4FrXRy9C744xc1Jb%2F7QVxtGdY9F0L4%2BATcd4ubq3%2BFjfSXW5mP3O6P5oSlLdSr35OgOhDLIC3fWSqolDPjjRuxMMCvbbW7v4aEU2W8MGZAjmDZwtAmOABEeQ%3D%3D%3A20200924181430%2Creg00140000003%2C120.195.119.104%2C20200924181430%2Cpjsag001401000000000000000000573%2C80EB14BF9E63A035907702CBEC093354%2C%2C1%2C0%2C-1%2C%2C1%2C%2C-1%2C-3%2C1%2CEND&GuardEncType=2";
        mVideoView.setVideoURI(Uri.parse(path));
//        mVideoView.setVideoURI(Uri.parse("http://113.98.100.38:60100/sdcs/jsdd01.ts"));
        //播放res目录资源,固定写法
//        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + rawRes));

        //系统自带的视频播放控制器,可以生成默认的视频播放页面
//        MediaController controller = new MediaController(this);
//        mVideoView.setMediaController(controller);
//        controller.setMediaPlayer(mVideoView);
        if (!mVideoView.isPlaying()) {
            mVideoView.start();
        }
        mVideoView.setOnPreparedListener(mp -> {
            mediaPlayer = mp;
            Log.d(TAG, "onPrepared mVideoView.getDuration:" + mVideoView.getDuration());
            isComplete = false;
            tvVideoTime.setText(String.format("%d/%d", 0, mVideoView.getDuration() / 1000));
            //发送消息去实时获取播放进度
            mHandler.sendEmptyMessage(UPDATE_UI);
        });
        mVideoView.setOnCompletionListener(mp -> {
            Log.d(TAG, "onCompletion getDuration:" + mVideoView.getDuration());
            //播放结束
            isComplete = true;
            switchChoiceView(!isResultVideo);
            if (isResultVideo) {
                mVideoView.setVisibility(View.GONE);
            }
        });
//        //获取系统最大音量
//        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        //获取系统当前音量
//        int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        //设置系统音量
//        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
    }



    //切换选择页面
    private void switchChoiceView(boolean shouldRequest) {
        if (shouldRequest) {
            mVideoView.setVisibility(View.GONE);
            choiceRootLayout.setVisibility(View.VISIBLE);
            ivChoice1.requestFocus();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                View view = getCurrentFocus();
//                int[] local = new int[2];
//                if (view != null) {
//                    view.getLocationOnScreen(local);
//                    Log.w(MyConfig.KEY_TAG, "焦点:getCurrentFocus:" + view + ",getLocation:" + local[0] + "," + local[1]);
//                } else {
//                    Log.w(MyConfig.KEY_TAG, "焦点:getCurrentFocus:null" + ",getLocation:" + local[0] + "," + local[1]);
//                }
//            }
//        }, 0, 2000);
//    }

    @Override
    public void onClick(View v) {
        if (isOver || mVideoView == null || mVideoView.getVisibility() == View.GONE) return;
        switch (v.getId()) {
            case R.id.iv_choice1:
                choiceResult(ivChoice1, R.drawable.ip_05_anim);
                break;
            case R.id.iv_choice2:
                choiceResult(ivChoice2, R.drawable.ip_06_anim);
                break;
            case R.id.iv_choice3:
                choiceResult(ivChoice3, R.drawable.ip_07_anim);
                break;
            case R.id.btn_back:
                Log.d("SystemVideoActivity", "CurrentPosition:" + mVideoView.getCurrentPosition());
                mVideoView.seekTo(mVideoView.getCurrentPosition() - 10000);
                break;
            case R.id.btn_pre:
                Log.d("SystemVideoActivity", "CurrentPosition:" + mVideoView.getCurrentPosition());
                mVideoView.seekTo(mVideoView.getCurrentPosition() + 10000);
                //测试跳转时间超过视频时长的情况
//                mVideoView.seekTo(40000);
                break;
        }
    }

    private void choiceResult(ImageView image, int animRes) {
        if (correctIndex - 1 < 0) return;
        if (choiceLayout.indexOfChild(image) == correctIndex - 1) {
            //选对了
//            animBall(image);
            //备选方案,播放选择成功视频
            configVideoView(R.raw.mov_choose_right, true);
        } else {
            //选错了
//            bombChoice(image, animRes);
            //备选方案,播放选择失败视频
            configVideoView(R.raw.mov_choose_wrong, true);
        }
    }

    private void bombChoice(ImageView image, int animRes) {
        //选错后失败动画
        if (anim != null && anim.isRunning()) {
            anim.stop();
        }
        image.setImageResource(animRes);
        anim = (AnimationDrawable) image.getDrawable();
        anim.start();
        Toast.makeText(this, "选错了!", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (anim != null && anim.isRunning()) {
                    anim.stop();
                }
                isOver = true;
            }
        }, 1700);
    }

    private void animBall(ImageView image) {
//        Log.d("test", String.format("ivBall.getX():%s,ivBall.getY():%s," +
//                        "ivBall.getLeft():%s,ivBall.getBottom():%s," +
//                        "ivBall.getTranslationX():%s,ivBall.getTranslationY():%s"
//                , ivBall.getX(), ivBall.getY(), ivBall.getLeft()
//                , ivBall.getBottom(), ivBall.getTranslationX(), ivBall.getTranslationY()));
//        Log.d("test", String.format("image.getX():%s,image.getY():%s," +
//                        "image.getLeft():%s,image.getBottom():%s," +
//                        "image.getTranslationX():%s,image.getTranslationY():%s"
//                , image.getX(), image.getY(), image.getLeft()
//                , image.getBottom(), image.getTranslationX(), image.getTranslationY()));
//        Log.d("test", String.format("choiceLayout.getX():%s,choiceLayout.getY():%s," +
//                        "choiceLayout.getLeft():%s,choiceLayout.getBottom():%s," +
//                        "choiceLayout.getTranslationX():%s,choiceLayout.getTranslationY():%s"
//                , choiceLayout.getX(), choiceLayout.getY(), choiceLayout.getLeft()
//                , choiceLayout.getBottom(), choiceLayout.getTranslationX(), choiceLayout.getTranslationY()));
        //选对后平移球到对应位置
        Point ballPoint = new Point(ivBall.getX(), ivBall.getY());
        float imageCenterX = image.getLeft() + (float) (image.getWidth() / 2);
        float imageCenterY = image.getBottom();
        Point housePoint = new Point(imageCenterX, imageCenterY);
        currentPoint = new Point(0, 0);
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), ballPoint, housePoint);
        animator.addUpdateListener(animation -> {
            currentPoint = (Point) animation.getAnimatedValue();
//                Log.d("test", "currentPoint:" + currentPoint);
            ivBall.setX(currentPoint.x);
            ivBall.setY(currentPoint.y);
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isOver = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setDuration(2000).start();
    }

    //时间格式化视频播放时间
    private void formatSeconds(long millisecond) {
        int second = (int) (millisecond / 1000);
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;
        String str = "";
        if (hh != 0) {
            //02d代表至少两位的十进制整数,5会变成05
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            setVideoParam(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            setVideoParam(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(this, 240));
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    //横竖屏切换
    private void switchFullScreen() {
        if (isFullScreen) {
            //切换半屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            //切换全屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    //改变屏幕亮度
    private void changeBrightness(float deltaY) {
        WindowManager.LayoutParams attr = getWindow().getAttributes();
        brightness = attr.screenBrightness;
        float index = deltaY / screenHeight / 3;
        brightness += index;
        if (brightness > 1.0f) {
            brightness = 1.0f;
        }
        if (brightness < 0.01f) {
            brightness = 0.01f;
        }
        getWindow().setAttributes(attr);
    }

}
