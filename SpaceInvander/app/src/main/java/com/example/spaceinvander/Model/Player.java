package com.example.spaceinvander.Model;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class Player {
    private Bitmap mbitmap;
    private float mX;
    private float mY;
    private float batas;
    private boolean[] life = new boolean[3];
    private boolean end;

    public Player(float x, float y,Bitmap spaceship,float batas){
        this.mbitmap = spaceship;
        mX = x;
        mY = y;
        this.batas = batas;
        for(int i = 0 ; i < life.length ; i++){
            this.life[i] = true;
        }
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
        if(life[0]){
            if(life[1]){
                if (life[2]) {
                    System.out.println("darah 3 hilang");
                    this.life[2] = false;
                }
                else{
                    System.out.println("darah 2 hilang");
                    this.life[1] = false;
                }
            }
            else{
                System.out.println("darah 1 hilang");
                this.life[0] = false;
                this.end = true;
            }
        }
    }

    public boolean getLife1() {
        return this.life[0];
    }
    public boolean getLife2() {
        return this.life[1];
    }
    public boolean getLife3() {
        return this.life[2];
    }

    public boolean getEnd(){
        return this.end;
    }
}
