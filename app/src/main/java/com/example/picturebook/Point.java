package com.example.picturebook;

/**
 * Created by ncx on 2019/10/24
 */
public class Point {
    float x;
    float y;
    public Point(){

    }
    public Point(float x,float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
