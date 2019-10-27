package com.example.spaceinvander.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.Presenter.MainPresenter;

import java.util.ArrayList;

public class Game extends SurfaceView implements Runnable {
    private Thread gameThread;
    private volatile boolean isPlay;//cari tau apa itu volatile boolean
    private Paint mPaint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;//cari tau apa itu surfacceholder
    private int screenSizeX, screenSizeY, mCounter;
    private static int SCORE = 0;
    private static int METOEOR_DESTROYED = 0;
    private volatile boolean isGameOver;
    private volatile boolean isHighScore;
    private MainPresenter mp;

    public Game(Context context, int screenSizeX, int screenSizeY) {
        super(context);
        this.screenSizeX = screenSizeX;
        this.screenSizeY = screenSizeY;
        this.mCounter = 0;
        this.mp = new MainPresenter(context,screenSizeX,screenSizeY);
        //ntr mungkin ada class sharedpreferedmanager yang ngatur attribut yang di pake bersama

        this.mPaint = new Paint();
        this.surfaceHolder = getHolder();

        this.reset();
    }

    @Override
    public void run() {
        while (isPlay){
            if(!isGameOver){
                this.update();
                this.draw();
                this.control();
            }
        }
        Log.d("GameThread", "Run stopped");
    }

    public void reset(){
        SCORE = 0;
        this.mp = new MainPresenter(getContext(),this.screenSizeX,this.screenSizeY);
        isGameOver = false;
        isHighScore = false;
    }

    public void update(){
        this.mp.updatePlayar();
        if(mCounter%200 == 0){
            this.mp.fire();
        }

        ArrayList<Meteor> meteors = this.mp.getMeteors();
        for(Meteor m : meteors){
            m.update();

            if(Rect.intersects(m.getCekCollision(),this.mp.getPlayer().getmCekCollision())){
                m.destroy();
                isGameOver = true;
//                if(){
//                    //untuk update high score kalo uda kalah
//                }
            }

            for(Laser l : this.mp.getPlayer().getMlaser()){
                if(Rect.intersects(m.getCekCollision(),l.getmCekCollision())){
                    m.hit();
                    l.destroyEnemy();
                }
            }
        }
        boolean delete = true;
        while(delete){
            if(this.mp.getSizeMeteor()!=0){
                if(this.mp.getMeteor(0).getmY() > this.screenSizeY){
                    this.mp.getMeteors().remove(0);
                }
            }

            if(this.mp.getMeteors().size()==0 || this.mp.getMeteors().get(0).getmY()<= this.screenSizeY){
                delete = false;
            }
        }

        if(mCounter % 1000 == 0){
            this.mp.addMeteor(getContext(),this.screenSizeX,this.screenSizeY);
        }
    }

    public void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            Player player = this.mp.getPlayer();
            canvas.drawBitmap(player.getMbitmap(),player.getmX(),player.getmY(),mPaint);

            for(Meteor m : this.mp.getMeteors()){
                canvas.drawBitmap(m.getMbitmap(),m.getmX(),m.getmY(),mPaint);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void control(){
        try{
            if(mCounter == 10000){
                mCounter = 0;
            }
            gameThread.sleep(20);
            mCounter+=20;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        Log.d("GameThread","Main");
        isPlay = false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        isPlay = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isGameOver){

                }
                break;
        }
        return super.onTouchEvent(event);
    }
}

