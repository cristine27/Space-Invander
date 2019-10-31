package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Laser;

import java.util.ArrayList;
import java.util.List;

public class ThreadLaserMove implements Runnable{
    protected Thread myThread;
    protected ThreadHandler threadHandler;
    protected ArrayList<Laser> lasers = new ArrayList<>();
    protected boolean pause;

    public ThreadLaserMove(ThreadHandler handler, ArrayList<Laser> lasers){
        this.threadHandler = handler;
        this.myThread = new Thread(this);
        this.lasers = lasers;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        while(true){
            while(!pause){
                System.out.println("Thread Laser");
                for(int i = 0 ; i < lasers.size(); i++){
                    this.lasers.get(i).setmY(this.lasers.get(i).getmY() - 100);
                }
                this.threadHandler.setLasers(this.lasers);
                try {
                    Thread.sleep(100);
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
}

