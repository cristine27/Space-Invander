package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Meteor;

public class ThreadEnemy implements Runnable {
    protected Thread thread;
    protected Meteor meteor;

    public ThreadEnemy(Meteor meteor){
        this.thread = new Thread(this);
        this.meteor = meteor;
    }

    public void start(){
        this.thread.start();
    }

    @Override
    public void run() {
        while(this.meteor.getmY()<=this.meteor.getBatasY() && this.meteor.getmX()<=this.meteor.getBatasX()){
            int kecepatan = this.meteor.randomAngka();
            this.meteor.setKecepatan(kecepatan);
            this.meteor.setmX();
            this.meteor.setmY();
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
