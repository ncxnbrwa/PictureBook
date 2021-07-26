package com.example.picturebook;

import android.animation.TypeEvaluator;

/**
 * Created by ncx on 2019/10/24
 * 自定义坐标点计算的估值器
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        Point point = new Point();
        point.x = startValue.x + fraction * (endValue.x - startValue.x);
        point.y = startValue.y + fraction * (endValue.y - startValue.y);
        return point;
    }
}
