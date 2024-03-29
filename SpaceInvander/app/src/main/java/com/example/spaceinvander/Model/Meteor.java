package com.example.spaceinvander.Model;

import android.graphics.Bitmap;
import android.widget.TextView;

import java.util.Random;

public class Meteor {
    private Bitmap mbitmap;
    private float mX;
    private float mY;
    private float batasX;
    private float batasY;
    private float kecepatan;
    private boolean flag1;
    private boolean flag2;
    private int int_hits;
    private String str_hits;

    public Meteor(float mX, float mY,Bitmap enemy,float batasX,float batasY){
        this.mX = mX;
        this.mY = mY;
        this.mbitmap = enemy;
        this.batasX = batasX;
        this.batasY = batasY;
        this.kecepatan = 0;
        this.flag1 = false;
        this.flag2 = false;
        this.int_hits = 0;
        this.str_hits = this.int_hits+"";
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

    public float getBatasX() {
        return batasX;
    }

    public float getBatasY() {
        return batasY;
    }

    public float getKecepatan() {
        return kecepatan;
    }

    public void setKecepatan(float kecepatan) {
        this.kecepatan = kecepatan;
    }

    public int randomAngka(){
        Random rand = new Random();
        return rand.nextInt(300);
    }

    public void setmX() {
        if(flag1){
            this.mX+=this.kecepatan;
            flag1=false;
        }
        else {
            this.mX-=this.kecepatan;
            flag1=true;
        }
    }

    public void setmY() {
        if(flag2){
            this.mY+=this.kecepatan;
            this.flag2=false;
        }
        else {
            this.mY-=this.kecepatan;
            flag2=true;
        }
    }

    public void increateHit(){
        this.int_hits+= 1;
        this.str_hits = this.int_hits + "";
    }

    public String getHit() {
        return this.str_hits;
    }
}
