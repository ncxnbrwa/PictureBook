package com.example.picturebook.view.custom_study.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by ncx on 2021/5/7
 * 图层透明度绘制测试View
 */
public class CanvasOperationView extends View {
    private Paint mPaint, linePaint, bgPaint;
//    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
//            | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
//            | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public CanvasOperationView(Context context) {
        this(context, null);
    }

    public CanvasOperationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasOperationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        linePaint = new Paint();
        bgPaint = new Paint();
        linePaint.setStrokeWidth(4);
        bgPaint.setColor(Color.GRAY);
        linePaint.setColor(Color.RED);
    }

    private float px = 500, py = 500;

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect(0, 0, 500, 500, bgPaint);
//        canvas.save();
//        canvas.rotate(90, px / 2, py / 2);
//        //这里绘制的是向上的箭头,但是画布旋转了90度,所以最终效果是向右的箭头
//        canvas.drawLine(px / 2, 0, 0, py / 2, linePaint);
//        canvas.drawLine(px / 2, 0, px, py / 2, linePaint);
//        canvas.drawLine(px / 2, 0, px / 2, py, linePaint);
//        canvas.restore();
//        //画出的圆在右下角,如果不调用canvas.restore()方法,绘制的圆会出现在左下角
//        canvas.drawCircle(px - 100, py - 100, 50, linePaint);

        canvas.drawColor(Color.WHITE);
        canvas.translate(10, 10);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(75, 75, 75, mPaint);
        //保存图层带透明度
        canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, 0);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(125, 125, 75, mPaint);
        canvas.restore();
    }
}
