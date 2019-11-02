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
        this.pause = false;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        loop:
        while (true){
            if(this.handler.cekEnd()){
                break loop;
            }
            Laser laser = new Laser(this.mPlayer.getmX(),this.mPlayer.getmY());
            this.handler.setLaser(laser);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pause(){
        try {
            this.myThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        this.myThread.notifyAll();
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean getPause(){
        return this.pause;
    }
}
