package com.example.picturebook.view.exo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.picturebook.manager.CustomManager;
import com.shuyu.gsyvideoplayer.GSYVideoBaseManager;
import com.shuyu.gsyvideoplayer.player.IPlayerManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shuyu.gsyvideoplayer.utils.CommonUtil.hideNavKey;

/**
 * Created by guoshuyu on 2018/5/16.
 * 自定义管理器，连接自定义exo view 和 exo player，实现无缝切换效果
 */
public class GSYExoVideoManager extends GSYVideoBaseManager {
    public static final int SMALL_ID = com.shuyu.gsyvideoplayer.R.id.small_id;

    public static final int FULLSCREEN_ID = com.shuyu.gsyvideoplayer.R.id.full_id;

    public static String TAG = "GSYExoVideoManager";

//    @SuppressLint("StaticFieldLeak")
//    private static GSYExoVideoManager videoManager;

    //每一个播放器对应一个CustomManager
    private static Map<String, GSYExoVideoManager> sMap = new HashMap<>();


    private GSYExoVideoManager() {
        init();
    }

    /**
     * 单例管理器
     */
//    public static synchronized GSYExoVideoManager instance() {
//        if (videoManager == null) {
//            videoManager = new GSYExoVideoManager();
//        }
//        return videoManager;
//    }

    public static synchronized GSYExoVideoManager instance(String key) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalStateException("key not be empty");
        }
        GSYExoVideoManager videoManager = sMap.get(key);
        if (videoManager == null) {
            videoManager = new GSYExoVideoManager();
            sMap.put(key, videoManager);
        }
        return videoManager;
    }

    @Override
    protected IPlayerManager getPlayManager() {
        return new GSYExoPlayerManager();
    }

    public void prepare(List<String> urls, Map<String, String> mapHeadData, int index,  boolean loop, float speed, boolean cache, File cachePath, String overrideExtension) {
        if (urls.size() == 0) return;
        Message msg = new Message();
        msg.what = HANDLER_PREPARE;
        msg.obj = new GSYExoModel(urls, mapHeadData, index, loop, speed, cache, cachePath, overrideExtension);
        sendMessage(msg);
        if (needTimeOutOther) {
            startTimeOutBuffer();
        }
    }


    /**
     * 上一集
     */
    public void previous() {
        if (playerManager == null) {
            return;
        }
        ((GSYExoPlayerManager)playerManager).previous();
    }

    /**
     * 下一集
     */
    public void next() {
        if (playerManager == null) {
            return;
        }
        ((GSYExoPlayerManager)playerManager).next();
    }

    /**
     * 退出全屏，主要用于返回键
     *
     * @return 返回是否全屏
     */
    @SuppressWarnings("ResourceType")
    public static boolean backFromWindowFull(Context context, String key) {
        boolean backFrom = false;
        ViewGroup vp = (ViewGroup) (CommonUtil.scanForActivity(context)).findViewById(Window.ID_ANDROID_CONTENT);
        View oldF = vp.findViewById(FULLSCREEN_ID);
        if (oldF != null) {
            backFrom = true;
            hideNavKey(context);
            if (GSYExoVideoManager.instance(key).lastListener() != null) {
                GSYExoVideoManager.instance(key).lastListener().onBackFullscreen();
            }
        }
        return backFrom;
    }

    /**
     * 页面销毁了记得调用是否所有的video
     */
    public static void releaseAllVideos(String key) {
        if (GSYExoVideoManager.instance(key).listener() != null) {
            GSYExoVideoManager.instance(key).listener().onCompletion();
        }
        GSYExoVideoManager.instance(key).releaseMediaPlayer();
    }


    /**
     * 暂停播放
     */
    public static void onPause(String key) {
        if (GSYExoVideoManager.instance(key).listener() != null) {
            GSYExoVideoManager.instance(key).listener().onVideoPause();
        }
    }

    /**
     * 恢复播放
     */
    public static void onResume(String key) {
        if (GSYExoVideoManager.instance(key).listener() != null) {
            GSYExoVideoManager.instance(key).listener().onVideoResume();
        }
    }


    /**
     * 恢复暂停状态
     *
     * @param seek 是否产生seek动作,直播设置为false
     */
    public static void onResume(String key,boolean seek) {
        if (GSYExoVideoManager.instance(key).listener() != null) {
            GSYExoVideoManager.instance(key).listener().onVideoResume(seek);
        }
    }

    //暂停所有播放器
    public static void onPauseAll() {
        if (sMap.size() > 0) {
            for (Map.Entry<String, GSYExoVideoManager> header : sMap.entrySet()) {
                header.getValue().onPause(header.getKey());
            }
        }
    }

    //恢复播放所有播放器
    public static void onResumeAll() {
        if (sMap.size() > 0) {
            for (Map.Entry<String, GSYExoVideoManager> header : sMap.entrySet()) {
                header.getValue().onResume(header.getKey());
            }
        }
    }

    /**
     * 恢复暂停状态
     *
     * @param seek 是否产生seek动作
     */
    public static void onResumeAll(boolean seek) {
        if (sMap.size() > 0) {
            for (Map.Entry<String, GSYExoVideoManager> header : sMap.entrySet()) {
                header.getValue().onResume(header.getKey(), seek);
            }
        }
    }

    //清掉播放器数据
    public static void clearAllVideo() {
        if (sMap.size() > 0) {
            //遍历整个map
            for (Map.Entry<String, GSYExoVideoManager> header : sMap.entrySet()) {
                GSYExoVideoManager.releaseAllVideos(header.getKey());
            }
        }
        //清空map
        sMap.clear();
    }

    /**
     * 当前是否全屏状态
     *
     * @return 当前是否全屏状态， true代表是。
     */
    @SuppressWarnings("ResourceType")
    public static boolean isFullState(Activity activity) {
        ViewGroup vp = (ViewGroup) (CommonUtil.scanForActivity(activity)).findViewById(Window.ID_ANDROID_CONTENT);
        final View full = vp.findViewById(FULLSCREEN_ID);
        GSYVideoPlayer gsyVideoPlayer = null;
        if (full != null) {
            gsyVideoPlayer = (GSYVideoPlayer) full;
        }
        return gsyVideoPlayer != null;
    }
}
