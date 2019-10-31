package com.example.spaceinvander.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.spaceinvander.R;

public class Laser {
    private Bitmap mbitmap;
    private float mX;
    private float mY;
    private Rect mCekCollision;

    public Laser(float posisiX, float posisiY){
        this.mX = posisiX;
        this.mY = posisiY;
    }

    public float getmY() {
        return mY;
    }

    public float getmX() {
        return mX;
    }

    public void setmY(float y) {
        this.mY = y;
    }

    public void setmX(float x) {
        this.mX = x;
    }

    public Bitmap getMbitmap() {
        return mbitmap;
    }
}
