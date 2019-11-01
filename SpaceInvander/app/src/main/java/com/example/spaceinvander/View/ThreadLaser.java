package com.example.spaceinvander.View;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.R;

import java.util.ArrayList;

public class ThreadLaser implements Runnable {
    protected Thread mThread;
    protected Player mPlayer;
    protected ThreadHandler handler;
    protected boolean pause = false;
    protected Bitmap laser;

    public ThreadLaser(ThreadHandler threadHandler, Player player, Bitmap laser){
        this.handler = threadHandler;
        this.mThread = new Thread(this);
        this.mPlayer = player;
        this.laser = laser;
    }
    public void start(){
        this.mThread.start();
    }

    @Override
    public void run() {
<<<<<<< Updated upstream
        while(true){
            while(!pause){
                Laser newLaser = new Laser(mPlayer.getmX(), mPlayer.getmY(), this.laser);
                this.handler.setLaser(newLaser);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
=======
        while (true){
            Laser laser = new Laser(this.mPlayer.getmX(),this.mPlayer.getmY());
            System.out.println("null ?");
            this.handler.setLaser(laser);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
>>>>>>> Stashed changes
            }
        }
    }
}
