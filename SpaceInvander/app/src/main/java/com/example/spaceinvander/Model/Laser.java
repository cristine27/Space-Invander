package com.example.spaceinvander.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.spaceinvander.R;

public class Laser {
    private Bitmap mbitmap;
    private int mX;
    private int mY;
    private Rect mCekCollision;
    private int mScreenSizeX;
    private int mScreenSizeY;
    private boolean isEnemy;

    public Laser(Context context, int mScreenSizeX, int mScreenSizeY, int posisiX, int posisiY, Bitmap spaceShip, boolean isEnemy){
        this.mScreenSizeX = mScreenSizeX;
        this.mScreenSizeY = mScreenSizeY;
        this.isEnemy = isEnemy;

        mbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
        mbitmap = Bitmap.createScaledBitmap(mbitmap, mbitmap.getWidth() * 3/5, mbitmap.getHeight() * 3/5,false);

        mX = posisiX + spaceShip.getWidth()/2 - mbitmap.getWidth()/2;
        if(isEnemy){
            mY = posisiY + mbitmap.getHeight() + 10;
        }
        else{
            mY = posisiY - mbitmap.getHeight() - 10;
        }

        mCekCollision = new Rect(mX, mY, mX + mbitmap.getWidth(), mY + mbitmap.getHeight());
    }

    public void update(){
        if(isEnemy){
            mY += mbitmap.getHeight() + 10;
            mCekCollision.left = mX;
            mCekCollision.right = mX + mbitmap.getWidth();
            mCekCollision.top = mY;
            mCekCollision.right = mY + mbitmap.getHeight();
        }
        else{
            mY -= mbitmap.getHeight() - 10;
            mCekCollision.left = mX;
            mCekCollision.right = mX + mbitmap.getWidth();
            mCekCollision.top = mY;
            mCekCollision.right = mY + mbitmap.getHeight();
        }
    }

    public boolean isEnemy(){
        return isEnemy;
    }

    public Rect getmCekCollision() {
        return mCekCollision;
    }

    public Bitmap getMbitmap() {
        return mbitmap;
    }

    public int getmY() {
        return mY;
    }

    public int getmX() {
        return mX;
    }

    public void destroyEnemy(){
        if(isEnemy){
            mY = mScreenSizeY;
        }
        else{
            mY = 0 - mbitmap.getHeight();
        }
    }
}
