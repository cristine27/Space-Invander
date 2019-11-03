package com.example.spaceinvander.View;

import android.media.Image;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class ThreadLaserMove implements Runnable{
    protected Thread myThread;
    protected ThreadHandler threadHandler;
    protected ArrayList<Laser> lasers = new ArrayList<>();
    protected boolean pause;
    protected Meteor enemy;
    protected boolean end;

    public ThreadLaserMove(ThreadHandler handler, ArrayList<Laser> lasers,Meteor enemy){
        this.threadHandler = handler;
        this.myThread = new Thread(this);
        this.lasers = lasers;
        this.enemy = enemy;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        loop:
        while(true){
            while(!pause){
                if(this.threadHandler.cekEnd()){
                    break loop;
                }
                for(int i = 0 ; i < lasers.size(); i++){
                    if (Math.abs(this.lasers.get(i).getmX() - this.enemy.getmX()) < 75 && Math.abs(this.lasers.get(i).getmY() - this.enemy.getmY()) < 350) {
                        this.enemy.increateHit();
                        this.lasers.remove(i);
                        this.threadHandler.setLasers(this.lasers);
                        this.threadHandler.increaseHit();
                    }
                    this.lasers.get(i).setmY(this.lasers.get(i).getmY() - 20);
                }
                this.threadHandler.setLasers(this.lasers);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setLasers(ArrayList<Laser> bullets) {
        this.lasers = bullets;
    }

    public void setPaused(boolean pause) {
        this.pause = pause;
    }

    public void setEndGame(){
        this.end = true;
    }
}

