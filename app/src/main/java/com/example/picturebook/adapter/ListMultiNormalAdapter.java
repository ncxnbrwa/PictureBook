package com.example.picturebook.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.picturebook.MyConfig;
import com.example.picturebook.R;
import com.example.picturebook.model.VideoModel;
import com.example.picturebook.view.MultiSampleVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 多个播放的listview adapter
 */

public class ListMultiNormalAdapter extends BaseAdapter {

    public static final String TAG = "ListMultiNormalAdapter";

    private List<VideoModel> list = new ArrayList<>();
    private LayoutInflater inflater = null;
    private Context context;

    private String fullKey = "null";

    public ListMultiNormalAdapter(Context context) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < 3; i++) {
            list.add(new VideoModel());
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_video_item_mutli, null);
            holder.gsyVideoPlayer = convertView.findViewById(R.id.video_item_player);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //设置随机的播放链接
//        final String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        final String url = MyConfig.GDYD_WJ_VIDEO_URL[(int) (MyConfig.GDYD_WJ_VIDEO_URL.length * Math.random())];
//        final String url = MyConfig.GDYD_DJ_VIDEO_URL[(int) (MyConfig.GDYD_DJ_VIDEO_URL.length * Math.random())];

        //多个播放时必须在setUpLazy、setUp和getGSYVideoManager()等前面设置
        holder.gsyVideoPlayer.setPlayTag(TAG);
        holder.gsyVideoPlayer.setPlayPosition(position);

        boolean isPlaying = holder.gsyVideoPlayer.getCurrentPlayer().isInPlayingState();

        if (!isPlaying) {
//            holder.gsyVideoPlayer.setUpLazy(url, false, null, null, "这是title");
//            holder.gsyVideoPlayer.setUp(url, true, "标题");
//            holder.gsyVideoPlayer.startPlayLogic();
//            holder.gsyVideoPlayer.setGSYVideoProgressListener((progress, secProgress, currentPosition, duration) -> {
//                Log.d(TAG, holder.gsyVideoPlayer + "==>progress:" + progress
//                        + " secProgress:" + secProgress + " currentPosition:" + currentPosition
//                        + " duration:" + duration);
//            });
            playRandomVideo(holder.gsyVideoPlayer);
        }

        //增加title
        holder.gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);

        //设置返回键
        holder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
//        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(v -> resolveFullBtn(holder.gsyVideoPlayer));
//        holder.gsyVideoPlayer.setRotateViewAuto(true);
//        holder.gsyVideoPlayer.setLockLand(true);
//        holder.gsyVideoPlayer.setReleaseWhenLossAudio(false);
//        holder.gsyVideoPlayer.setShowFullAnimation(true);
//        holder.gsyVideoPlayer.setIsTouchWiget(false);

//        holder.gsyVideoPlayer.setNeedLockFull(true);

        if (position % 2 == 0) {
            holder.gsyVideoPlayer.loadCoverImage(url, R.mipmap.cover1);
        } else {
            holder.gsyVideoPlayer.loadCoverImage(url, R.mipmap.cover2);
        }

//        holder.gsyVideoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
//            @Override
//            public void onQuitFullscreen(String url, Object... objects) {
//                super.onQuitFullscreen(url, objects);
//                fullKey = "null";
//            }
//
//            @Override
//            public void onEnterFullscreen(String url, Object... objects) {
//                super.onEnterFullscreen(url, objects);
//                holder.gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
//                fullKey = holder.gsyVideoPlayer.getKey();
//            }

//            @Override
//            public void onAutoComplete(String url, Object... objects) {
//                super.onAutoComplete(url, objects);
//
//            }
//        });

        return convertView;
    }

    //随机播放视频,初始化播放器
    private void playRandomVideo(MultiSampleVideo gsyVideoPlayer) {
        // 打开硬解码
        GSYVideoType.enableMediaCodec();
        GSYVideoType.enableMediaCodecTexture();
        //切换播放器内核
//        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        //降低帧数,视频码率太高或在模拟器上会有音视频不同步的情况
        VideoOptionModel frameDropOption =
                new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 50);
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(frameDropOption);
        GSYVideoManager.instance().setOptionModelList(list);
        String url = MyConfig.GDYD_WJ_VIDEO_URL[(int) (MyConfig.GDYD_WJ_VIDEO_URL.length * Math.random())];
//        String url = MyConfig.GDYD_DJ_VIDEO_URL[(int) (MyConfig.GDYD_DJ_VIDEO_URL.length * Math.random())];
//        String url = MyConfig.PATHS[(int) (MyConfig.PATHS.length * Math.random())];
        gsyVideoPlayer.setUp(url, true, "标题");
        gsyVideoPlayer.startPlayLogic();
        //视频title设置隐藏
        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        gsyVideoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {

            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //设置静音
//                GSYVideoManager.instance().setNeedMute(true);
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
                Log.e(TAG,"gsyVideoPlayer onAutoComplete");
                playRandomVideo((MultiSampleVideo) objects[1]);
            }

            @Override
            public void onPlayError(String url, Object... objects) {
                super.onPlayError(url, objects);
                Log.e(TAG,"gsyVideoPlayer onPlayError");
                playRandomVideo((MultiSampleVideo) objects[1]);
            }
        });
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, false, true);
    }

    class ViewHolder {
        MultiSampleVideo gsyVideoPlayer;
    }


    public String getFullKey() {
        return fullKey;
    }

}
