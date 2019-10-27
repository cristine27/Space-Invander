package com.example.spaceinvander.Presenter;

import android.content.Context;
import android.graphics.Rect;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;

import java.util.ArrayList;

public class MainPresenter {
    private Player player;
    private ArrayList<Meteor> meteors;
    private ArrayList<Laser> lasers;

    public MainPresenter(Context context, int screenSizeX, int screenSizeY){
        this.player = new Player(context,screenSizeX,screenSizeY);
        this.meteors = new ArrayList<>();
        this.lasers = new ArrayList<>();
    }

    public void steerRight(float speed){
        this.player.moveRight(speed);
    }

    public void steerLeft(float speed){
        this.player.moveLeft(speed);
    }

    public void stay(){
        this.player.stay();
    }

    public void updatePlayar(){
        this.player.update();
    }

    public void fire(){
        this.player.fire();
    }

    public Rect getCollisionPlayer(){
        return this.player.getmCekCollision();
    }

    public int getSizeMeteor(){
        return this.meteors.size();
    }

    public Meteor getMeteor(int i){
        return this.meteors.get(i);
    }

    public void addMeteor(Context context, int sX, int sY){
        this.meteors.add(new Meteor(context,sX,sY));
    }

    public Laser getLaser(int i){
        return this.lasers.get(i);
    }

    public Player getPlayer(){
        return this.player;
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public ArrayList<Meteor> getMeteors() {
        return meteors;
    }
}
