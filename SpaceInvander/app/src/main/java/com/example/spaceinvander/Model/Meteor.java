package com.example.spaceinvander.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.spaceinvander.R;

import java.util.Random;

public class Meteor {
    private Bitmap mbitmap;
    private int mX;
    private int mY;
    private int batasMaxX;
    private int batasMinX;
    private int batasMaxY;
    private int batasMinY;

    private int kecepatan;
    private Rect cekCollision;
    private int screenSizeX;
    private int screenSizeY;
    private int Mhp;

    public Meteor(Context context, int screenSizeX, int screenSizeY){
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;

        mbitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteor);
        mbitmap = Bitmap.createScaledBitmap(mbitmap, mbitmap.getWidth() * 3/5, mbitmap.getHeight()* 3/5,false);

        batasMaxX = screenSizeX - mbitmap.getWidth();
        batasMaxY = screenSizeY - mbitmap.getHeight();
        batasMinX = 0;
        batasMinY = 0;
        Mhp = 2;

        Random rand = new Random();
        kecepatan = rand.nextInt(3)+1;

        mX = rand.nextInt(batasMaxX);
        mY = 0 - mbitmap.getHeight();

        cekCollision = new Rect(mX,mY, mX+mbitmap.getWidth(),mY+mbitmap.getHeight());
    }

    public void update(){
        mY += 5 * kecepatan;

        cekCollision.left = mX;
        cekCollision.right = mX + mbitmap.getWidth();
        cekCollision.top = mY;
        cekCollision.left = mY + mbitmap.getHeight();
    }

    public Rect getCekCollision(){
        return cekCollision;
    }

    public void destroy(){
        mY = screenSizeY + 1;
    }

    public Bitmap getMbitmap(){
        return mbitmap;
    }

    public int getmX(){
        return mX;
    }

    public int getmY(){
        return mY;
    }

    public void hit(){
        if(--Mhp==0){
//            bagian ini bakal di tambah sama atribut global score
//            dan atribut untuk hitung berapa banyak meteor yang di hancurin
            destroy();
        }
    }
}
