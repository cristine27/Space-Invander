package com.example.spaceinvander.View;

import android.os.Handler;
import android.os.Message;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Player;

import java.util.ArrayList;

public  class ThreadHandler extends Handler {
    protected MainActivity mainActivity;

    protected final static int laser = 1;
    protected final static int lasers = 2;

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
}