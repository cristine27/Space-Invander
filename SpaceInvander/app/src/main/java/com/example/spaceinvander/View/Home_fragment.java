package com.example.spaceinvander.View;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.spaceinvander.Presenter.MainPresenter;
import com.example.spaceinvander.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_fragment extends Fragment {
    protected static Home_fragment home;
    protected Button btn_play;
    protected Button btn_exit;
    protected int width=0;
    protected int height=0;
    protected MainPresenter presenter;

    public Home_fragment() {
        // Required empty public constructor
    }

    public static Home_fragment createHome(MainPresenter presenter){
        if(home==null){
            home = new Home_fragment();
            home.presenter = presenter;
        }
        return home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        width = getWidth();
        height = getHeight();
        System.out.println("ini h w"+width+" "+height);
        this.btn_play = view.findViewById(R.id.btn_play);
        this.btn_exit = view.findViewById(R.id.btn_exit);

        this.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity ma = (MainActivity)getActivity();
                presenter.setHeight(height);
                presenter.setWidth(width);
                presenter.changePage(2);
            }
        });
        this.btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity ma = (MainActivity)getActivity();
                ma.finish();
                System.exit(0);
            }
        });

        return view;
    }

    public static Home_fragment newInstance() {
        Home_fragment fragment = new Home_fragment();
        return fragment;
    }


    public int getWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public int getHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
