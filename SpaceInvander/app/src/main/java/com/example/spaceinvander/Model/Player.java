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

    private Rect mCekCollision;
    private ArrayList<Laser> mlaser;
    private int mScreenSizeX, mScreenSizeY;
    public Player(float x, float y,Bitmap spaceship,float batas){
        this.mbitmap = spaceship;
        mX = x;
        mY = y;
        mlaser = new ArrayList<>();
        this.batas = batas;
        mCekCollision = new Rect((int)mX, (int)mY, (int)mX + mbitmap.getWidth(), (int)mY + mbitmap.getHeight());
    }

    public void update(){

        mCekCollision.left = (int)mX;
        mCekCollision.top = (int)mY;
        mCekCollision.right = (int)mX + mbitmap.getWidth();
        mCekCollision.bottom = (int)mY + mbitmap.getHeight();

        for(Laser laser : mlaser){
            laser.update();
        }

        boolean deleting = true;
        while (deleting){
            if(mlaser.size() != 0){
                if(mlaser.get(0).getmY() < 0 ){
                    mlaser.remove(0);
                }
            }

            if(mlaser.size()==0 || mlaser.get(0).getmY() >= 0){
                deleting =false;
            }
        }
    }

    public Rect getmCekCollision() {
        return mCekCollision;
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
