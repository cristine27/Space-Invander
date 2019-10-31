package com.example.spaceinvander.Presenter;

import com.example.spaceinvander.View.ActivityInterface;
import com.example.spaceinvander.View.GameInterface;
import com.example.spaceinvander.View.MainActivity;


public class MainPresenter {
    ActivityInterface ai;
    GameInterface gi;

    public MainPresenter(MainActivity ma){
        this.ai = ma;
        this.gi = gi;
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
}
