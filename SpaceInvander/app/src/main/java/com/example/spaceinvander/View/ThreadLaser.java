package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Player;

public class ThreadLaser implements Runnable{

    protected Thread myThread;
    protected ThreadHandler handler;
    protected Player mPlayer;
    protected boolean pause;

    public ThreadLaser(ThreadHandler handler, Player player){
        this.myThread = new Thread(this);
        this.handler = handler;
        this.mPlayer = player;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        while (true){
            Laser laser = new Laser(this.mPlayer.getmX(),this.mPlayer.getmY());
            System.out.println("null ?");
            this.handler.setLaser(laser);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
