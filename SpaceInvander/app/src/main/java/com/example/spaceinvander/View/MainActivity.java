package com.example.spaceinvander.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.spaceinvander.Presenter.MainPresenter;
import com.example.spaceinvander.R;

public class MainActivity extends AppCompatActivity implements ActivityInterface,GameInterface{
    protected Home_fragment home_fragment;
    protected Fragment_play fragment_play;
    protected FragmentManager fragmentManager;
    protected FrameLayout frame_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
        this.frame_container = findViewById(R.id.frame_container);
        this.home_fragment = Home_fragment.createHome(presenter);
        this.fragment_play = Fragment_play.createGame(presenter,this);

        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.frame_container, this.home_fragment).addToBackStack(null).commit();
    }


    @Override
    public void changePage(int i) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(i==1){
            if(this.home_fragment.isAdded()){
                ft.show(this.home_fragment);
            }
            else{
                ft.add(R.id.frame_container,this.home_fragment);
            }

            if(this.fragment_play.isAdded()){
                ft.hide(this.fragment_play);
            }
        }else if(i==2){
            if(this.fragment_play.isAdded()){
                ft.show(this.fragment_play);
            }
            else{
                ft.add(R.id.frame_container,this.fragment_play);
            }

            if(this.home_fragment.isAdded()){
                ft.hide(this.home_fragment);
            }
        }
        ft.commit();
    }

    @Override
    public void setWidth(int w) {
        this.fragment_play.setBitmapW(w);
    }

    @Override
    public void setHeight(int h) {
        this.fragment_play.setBitmapH(h);
    }
}
