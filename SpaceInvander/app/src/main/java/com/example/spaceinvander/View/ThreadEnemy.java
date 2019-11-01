package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Meteor;

import java.util.Random;

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
        while(this.meteor.getmY()+30<=this.meteor.getBatasY()/2+this.meteor.getMbitmap().getHeight() || this.meteor.getmX()-30<=this.meteor.getBatasX()/2+this.meteor.getMbitmap().getWidth()){
            int kecepatan = this.meteor.randomAngka();
            this.meteor.setKecepatan(kecepatan);
            Random rand = new Random();
            int angkaRand = rand.nextInt(10);
            if(angkaRand<=5){
                this.meteor.setmX();
            }
            else{
                this.meteor.setmY();
            }
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
