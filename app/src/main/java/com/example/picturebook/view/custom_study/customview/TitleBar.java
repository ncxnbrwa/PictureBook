package com.example.picturebook.view.custom_study.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.picturebook.R;

/**
 * Created by ncx on 2021/5/12
 */
public class TitleBar extends RelativeLayout {
    private ImageView ivLeft, ivRight;
    private TextView tvCenter;
    private RelativeLayout rootLayout;
    private int mColor;
    private int mTextColor;
    private String titleName;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        mColor = ta.getColor(R.styleable.TitleBar_title_bg, Color.BLUE);
        mTextColor = ta.getColor(R.styleable.TitleBar_title_text_color, Color.BLACK);
        titleName = ta.getString(R.styleable.TitleBar_title_text);
        ta.recycle();

        LayoutInflater.from(context).inflate(R.layout.title_layout, this, true);
        ivLeft = findViewById(R.id.iv_title_left);
        ivRight = findViewById(R.id.iv_title_right);
        rootLayout = findViewById(R.id.root_layout);
        tvCenter = findViewById(R.id.tv_title_center);
        rootLayout.setBackgroundColor(mColor);
        tvCenter.setTextColor(mTextColor);
        if (!TextUtils.isEmpty(titleName)) {
            tvCenter.setText(titleName);
        }
    }

    public void setTitle(String titleName) {
        if (!TextUtils.isEmpty(titleName)) {
            tvCenter.setText(titleName);
        }
    }

    public void setLeftListener(OnClickListener leftListener) {
        ivLeft.setOnClickListener(leftListener);
    }

    public void setRightListener(OnClickListener rightListener) {
        ivRight.setOnClickListener(rightListener);
    }
}
