package com.example.spaceinvander.View;

import android.os.Handler;
import android.os.Message;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Player;

import java.util.ArrayList;

public  class ThreadHandler extends Handler {
    protected MainActivity mainActivity;

    protected final static int health = 0;
    protected final static int laser = 1;
    protected final static int lasers = 2;
    protected final static int enemyLaser = 3;
    protected final static int enemyLasers = 4;
    protected final static int life = 5;
    protected final static int gameover = 6;
    protected final static int cekEnd = 7;

    public ThreadHandler (MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg){
        if(msg.what == this.laser){
            Laser laser = (Laser) msg.obj;
            this.mainActivity.fragment_play.setLaser(laser);
        }else if(msg.what== this.lasers){
            ArrayList<Laser> lasers = (ArrayList<Laser>) msg.obj;
            this.mainActivity.fragment_play.setLasers(lasers);
        }else if(msg.what== this.enemyLaser){
            Laser enemyLaser = (Laser) msg.obj;
            this.mainActivity.fragment_play.setEnemyLaser(enemyLaser);
        }else if(msg.what== this.enemyLasers){
            ArrayList<Laser> enemyLasers = (ArrayList<Laser>) msg.obj;
            this.mainActivity.fragment_play.setEnemyLasers(enemyLasers);
        }else if(msg.what == this.health){
            this.mainActivity.fragment_play.increaseHit();
        }else if(msg.what == this.life){
            this.mainActivity.fragment_play.decreaseLife();
        }
    }

    public void setLaser(Laser laser){
        Message msg = new Message();
        msg.what = this.laser;
        msg.obj = laser;
        this.sendMessage(msg);
    }

    public void setLasers(ArrayList<Laser> lasers){
        Message msg = new Message();
        msg.what = this.lasers;
        msg.obj = lasers;
        this.sendMessage(msg);
    }

    public void setEnemyLaser(Laser laser){
        Message msg = new Message();
        msg.what = this.enemyLaser;
        msg.obj = laser;
        this.sendMessage(msg);
    }

    public void setEnemyLasers(ArrayList<Laser> lasers){
        Message msg = new Message();
        msg.what = this.enemyLasers;
        msg.obj = lasers;
        this.sendMessage(msg);
    }

    public void increaseHit(){
        Message msg = new Message();
        msg.what = this.health;
        this.sendMessage(msg);
    }

    public void decreaseLife(){
        Message msg = new Message();
        msg.what = this.life;
        this.sendMessage(msg);
    }

    public void setEnd(){
        Message msg = new Message();
        msg.what = this.gameover;
        this.sendMessage(msg);
    }

    public boolean cekEnd(){
        Message msg = new Message();
        msg.what = this.cekEnd;
        this.sendMessage(msg);
        return this.mainActivity.fragment_play.getGameover();
    }
}