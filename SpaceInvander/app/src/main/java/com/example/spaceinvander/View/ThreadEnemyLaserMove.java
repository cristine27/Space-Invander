package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Player;

import java.util.ArrayList;

public class ThreadEnemyLaserMove implements Runnable{

    protected Thread myThread;
    protected ThreadHandler threadHandler;
    protected ArrayList<Laser> lasers = new ArrayList<>();
    protected boolean pause;
    protected Player mPlayer;
    protected boolean end;

    public ThreadEnemyLaserMove(ThreadHandler handler, ArrayList<Laser> lasers,Player player){
        this.threadHandler = handler;
        this.myThread = new Thread(this);
        this.lasers = lasers;
        this.mPlayer = player;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        loop:
        while(true){
            while(!pause){
                for(int i = 0 ; i < lasers.size(); i++){
                    if (Math.abs(this.lasers.get(i).getmX() - this.mPlayer.getmX()) < 150 && Math.abs(this.lasers.get(i).getmY() - this.mPlayer.getmY()) < 250) {
                        if(!this.mPlayer.getEnd()){
                            this.mPlayer.decreaseHeart();
                            this.lasers.remove(i);
                            this.threadHandler.decreaseLife();
                            this.threadHandler.setEnemyLasers(this.lasers);
                        }else{
                            this.threadHandler.setEnd();
                            break loop;
                        }
                    }
                    this.lasers.get(i).setmY(this.lasers.get(i).getmY() + 20);
                }
                this.threadHandler.setEnemyLasers(this.lasers);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setEnemyLasers(ArrayList<Laser> bullets) {
        this.lasers = bullets;
    }

    public void setPaused(boolean pause) {
        this.pause = pause;
    }
}

