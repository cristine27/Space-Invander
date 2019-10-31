package com.example.spaceinvander.Model;

import android.graphics.Bitmap;

import java.util.Random;

public class Meteor {
    private Bitmap mbitmap;
    private float mX;
    private float mY;
    private float batas;
    private float kecepatan;

    public Meteor(float mX, float mY,Bitmap enemy,float batas){
        this.mX = mX;
        this.mY = mY;
        this.mbitmap = enemy;
        this.batas = batas;
        this.kecepatan = 0;
    }

    public Bitmap getMbitmap(){
        return mbitmap;
    }

    public float getmX(){
        return mX;
    }

    public float getmY() {
        return mY;
    }

    public float getBatas() {
        return batas;
    }

    public float getKecepatan() {
        return kecepatan;
    }

    public void setKecepatan(float kecepatan) {
        this.kecepatan = kecepatan;
    }

    public int randomAngka(){
        Random rand = new Random();
        return rand.nextInt(50);
    }

    public void setmX() {
        this.mX = mX+this.kecepatan;
    }

    public void setmY() {
        this.mY = mY+this.kecepatan;
    }
}
