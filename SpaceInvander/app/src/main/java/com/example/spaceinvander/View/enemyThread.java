package com.example.spaceinvander.View;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class enemyThread implements Runnable{
    protected float posX,posY;
    protected Random r;
    protected ScheduledExecutorService executor;
    protected enemyHandler eh;
    protected int index;

    public enemyThread(enemyHandler eh, float [] temp){
        this.r = new Random();
        this.posX = temp[0];
        this.posY = temp[1];
        this.eh = eh;
    }

    public void runThread(){
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this,0,100, TimeUnit.MILLISECONDS);
    }

    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public void run() {
        int rnd;

    }
}
