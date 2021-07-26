package com.example.picturebook.view.custom_study.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by ncx on 2021/5/12
 * 中间划线的TextView
 */
public class InvalidTextView extends AppCompatTextView {
    private Paint mPaint;

    public InvalidTextView(Context context) {
        this(context, null);
    }

    public InvalidTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InvalidTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, (float) getHeight() / 2, getWidth(), (float) getHeight() / 2, mPaint);
    }
}
