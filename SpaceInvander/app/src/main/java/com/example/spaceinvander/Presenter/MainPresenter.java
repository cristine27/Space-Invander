package com.example.spaceinvander.Presenter;

import com.example.spaceinvander.View.ActivityInterface;
import com.example.spaceinvander.View.GameInterface;
import com.example.spaceinvander.View.MainActivity;


public class MainPresenter {
    ActivityInterface ai;
    GameInterface gi;
    int sensorValue;

    public MainPresenter(MainActivity ma){
        this.ai = ma;
        this.gi = ma;
    }

    public void changePage(int i){
        this.ai.changePage(i);
    }

    public void setWidth(int w){
        this.gi.setWidth(w);
    }

    public void setHeight(int h){
        this.gi.setHeight(h);
    }

    public double getSensorValue(){
        return this.ai.getSensorValue();
    }
}
