package com.example.spaceinvander.View;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;

import java.util.ArrayList;
import java.util.List;

public class LaserMoveThread implements Runnable{
    protected Thread myThread;
    protected ThreadHandler threadHandler;
<<<<<<< Updated upstream:SpaceInvander/app/src/main/java/com/example/spaceinvander/View/LaserMoveThread.java
    protected ArrayList<Laser> lasers;
    protected boolean pause;

    public LaserMoveThread(ThreadHandler handler, ArrayList<Laser> lasers){
=======
    protected ArrayList<Laser> lasers = new ArrayList<>();
    protected Meteor enemy;

    protected boolean pause;

    public ThreadLaserMove(ThreadHandler handler, ArrayList<Laser> lasers, Meteor enemy){
>>>>>>> Stashed changes:SpaceInvander/app/src/main/java/com/example/spaceinvander/View/ThreadLaserMove.java
        this.threadHandler = handler;
        this.myThread = new Thread(this);
        this.enemy = enemy;
        this.lasers = lasers;
    }

    public void start(){
        this.myThread.start();
    }

    @Override
    public void run() {
        while(true){
            while(!pause){
                for(int i = 0 ; i < lasers.size(); i++){
<<<<<<< Updated upstream:SpaceInvander/app/src/main/java/com/example/spaceinvander/View/LaserMoveThread.java
                    this.threadHandler.setLasers(this.lasers);
=======
                    System.out.println("collision ???");
                    System.out.println("x = " + Math.abs(this.lasers.get(i).getmX() - this.enemy.getmX()));
                    System.out.println("y = " + Math.abs(this.lasers.get(i).getmY() - this.enemy.getmY()));
                    if (Math.abs(this.lasers.get(i).getmX() - this.enemy.getmX()) < 75 && Math.abs(this.lasers.get(i).getmY() - this.enemy.getmY()) < 350) {
                        System.out.println("yes");
                        this.lasers.remove(i);
                        this.threadHandler.setLasers(this.lasers);
                    }
>>>>>>> Stashed changes:SpaceInvander/app/src/main/java/com/example/spaceinvander/View/ThreadLaserMove.java
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
