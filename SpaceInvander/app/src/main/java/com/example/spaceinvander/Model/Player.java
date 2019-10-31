package com.example.spaceinvander.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.spaceinvander.R;

import java.util.ArrayList;

public class Player {
    private Bitmap mbitmap;

    private int mX;
    private int mY;
    private int mSpeed;
    private int batasMaxX;
    private int batasMinX;
    private int batasMaxY;
    private int batasMinY;
    private int mMargin = 16;
    private boolean isKiri, isKanan;
    private float kecepatanPindah;
    private Rect mCekCollision;
    private ArrayList<Laser> mlaser;
<<<<<<< Updated upstream
    private Context mContext;
    private int mScreenSizeX, mScreenSizeY;

    public Player(Context context, int screenSizeX, int screenSizeY){
        this.mScreenSizeX = screenSizeX;
        this.mScreenSizeY = screenSizeY;
        this.mContext = context;

        mSpeed = 1;
        mbitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.spaceship);
        mbitmap = Bitmap.createScaledBitmap(mbitmap, mbitmap.getWidth() * 3/5, mbitmap.getHeight() * 3/5, false);

        batasMaxX = screenSizeX - mbitmap.getWidth();
        batasMaxY = screenSizeY - mbitmap.getHeight();
        batasMinX = 0;
        batasMinY = 0;

        mX = screenSizeX/2 - mbitmap.getWidth()/2;
        mY = screenSizeY/2 - mbitmap.getHeight()/mMargin;

=======

    public Player(float x, float y,Bitmap spaceship,float batas){
        this.mbitmap = spaceship;
        mX = x;
        mY = y;
>>>>>>> Stashed changes
        mlaser = new ArrayList<>();

        mCekCollision = new Rect(mX, mY, mX + mbitmap.getWidth(), mY + mbitmap.getHeight());
    }

    public void update(){
<<<<<<< Updated upstream
        if(isKiri){
            mX -= 10 * kecepatanPindah;
            if(mX<batasMinX){
                mX = batasMinX;
            }
        }
        else if(isKanan){
            mX += 10 * kecepatanPindah;
            if(mX>batasMaxX){
                mX = batasMaxX;
            }
        }

        mCekCollision.left = mX;
        mCekCollision.top = mY;
        mCekCollision.right = mX + mbitmap.getWidth();
        mCekCollision.bottom = mY + mbitmap.getHeight();
=======
        mCekCollision.left = (int)mX;
        mCekCollision.top = (int)mY;
        mCekCollision.right = (int)mX + mbitmap.getWidth();
        mCekCollision.bottom = (int)mY + mbitmap.getHeight();
>>>>>>> Stashed changes

        for(Laser laser : mlaser){

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

    public void moveRight(float speed){
        isKiri = false;
        isKanan = true;
        kecepatanPindah = Math.abs(speed);
    }

    public void moveLeft(float speed){
        isKiri = true;
        isKanan = false;
        kecepatanPindah = Math.abs(speed);
    }

    public void stay(){
        isKiri = false;
        isKanan = false;
        kecepatanPindah = 0;
    }

    public int getmX() {
        return mX;
    }

    public int getmY() {
        return mY;
    }

    public void fire(){

    }
}
