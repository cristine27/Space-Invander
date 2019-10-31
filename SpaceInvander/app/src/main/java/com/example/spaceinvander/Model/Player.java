package com.example.spaceinvander.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.spaceinvander.R;

import java.util.ArrayList;

public class Player {
    private Bitmap mbitmap;

    private float mX;
    private float mY;
    private float batas;

    public Player(float x, float y,Bitmap spaceship,float batas){
        this.mbitmap = spaceship;
        mX = x;
        mY = y;

        mlaser = new ArrayList<>();
        this.batas = batas;
    }

    public ArrayList<Laser> getMlaser() {
        return mlaser;
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

    public float getmX() {
        return mX;
    }

    public float getmY() {
        return mY;
    }

    public void fire(){

    }
}
