package com.example.spaceinvander.Model;

import android.graphics.Bitmap;

public class Player {
    private Bitmap mbitmap;
    private int heart;
    private float mX;
    private float mY;
    private float batas;

    public Player(float x, float y,Bitmap spaceship,float batas){
        this.mbitmap = spaceship;
        mX = x;
        mY = y;
        this.batas = batas;
        this.heart = 300;
    }

    public Bitmap getMbitmap() {
        return mbitmap;
    }

    public void moveRight(){
        if(this.mX+30<=batas-this.mbitmap.getWidth()){
            this.mX+=30;
        }
    }

    public void moveLeft(){
        if(this.mX-30>=0){
            this.mX-=30;
        }
    }

    public void moveRightSensor(){
        if(this.mX+10<=batas-this.mbitmap.getWidth()){
            this.mX+=10;
        }
    }

    public void moveLeftSensor(){
        if(this.mX-10>=0){
            this.mX-=10;
        }
    }

    public float getmX() {
        return mX;
    }

    public float getmY() {
        return mY;
    }

    public void setmY(float mY) {
        this.mY = mY;
    }

    public void setmX(float mX) {
        this.mX = mX;
    }

    public void decreaseHeart(){
        this.heart-=30;
    }

    public int getHeart() {
        return heart;
    }
}
