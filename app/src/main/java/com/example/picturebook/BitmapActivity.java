package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class BitmapActivity extends AppCompatActivity {
    private static final String TAG = "BitmapActivity";
    private Bitmap reuseBmp;
    private ImageView image;
    private int[] resIds = {R.drawable.test2, R.drawable.test3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        image = findViewById(R.id.image);
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        options.inMutable = true;
//        TypedValue value = new TypedValue();
//        getResources().openRawResource(R.drawable.test, value);
//        options.inTargetDensity = value.density;
        options.inSampleSize = 2;//宽和高每隔两个像素进行一次采样
        Bitmap resBmp = BitmapFactory.decodeResource(getResources(), R.drawable.test3, options);
        Log.d(TAG, "resBmp size:" + resBmp.getAllocationByteCount());
//        try {
//            InputStream inputStream = getAssets().open("test.jpg");
//            Bitmap assetBmp = BitmapFactory.decodeStream(inputStream);
//            Log.d(TAG, "bmp size:" + assetBmp.getAllocationByteCount());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        showRegionImage();
    }

    private Bitmap getBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //这里第二个参数可以根据情况切换
        BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
        if (canUseForInBitmap(reuseBmp, options)) {
            options.inMutable = true;//不设置这个的话BitmapFactory将不会重复利用 Bitmap 内存,并输出相应warning日志
            options.inBitmap = reuseBmp;//把需要复用的Bitmap放入这个参数里面
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), R.drawable.test, options);
    }

    //判断是否可以重用任何 Bitmap 的内存区域,只要这块内存比将要分配内存的 bitmap 大就可以
    private boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / Math.max(targetOptions.inSampleSize, 1);
        int height = targetOptions.outHeight / Math.max(targetOptions.inSampleSize, 1);
        int byteCount = width * height * getBytesPerPixel(candidate.getConfig());
        return byteCount <= candidate.getAllocationByteCount();
    }

    //根据设置的Config模式确定Bitmap字节单位大小
    private int getBytesPerPixel(Bitmap.Config config) {
        int bytes;
        switch (config) {
            case ALPHA_8:
                bytes = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytes = 2;
                break;
            default:
                bytes = 4;
                break;
        }
        return bytes;
    }

    private void showRegionImage() {
        try {
            InputStream inputStream = getAssets().open("test3.jpg");
            //设置显示图片的中心区域
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bmp = regionDecoder.decodeRegion(new Rect(200, 200, 400, 400), options);
            image.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}