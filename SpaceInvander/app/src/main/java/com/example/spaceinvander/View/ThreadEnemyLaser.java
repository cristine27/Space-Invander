package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;

public class ThreadEnemyLaser implements Runnable{

    protected Thread myThread;
    protected ThreadHandler handler;
    protected Meteor mEnemy;
    protected boolean pause;
    protected boolean gameover;

    public ThreadEnemyLaser(ThreadHandler handler, Meteor enemy){
        this.myThread = new Thread(this);
        this.handler = handler;
        this.mEnemy = enemy;
        this.pause = false;
        this.gameover = false;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        loop:
        while (true){
            Laser laser = new Laser(this.mEnemy.getmX(),this.mEnemy.getmY());
            this.handler.setEnemyLaser(laser);
            if(this.handler.cekEnd()){
                break loop;
            }
            try {
                Thread.sleep(2000);
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
