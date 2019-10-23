package com.example.spaceinvander.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.spaceinvander.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_fragment extends Fragment implements View.OnClickListener {
    protected Button btn_play;
    protected Button btn_exit;
    public Home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        this.btn_play = view.findViewById(R.id.btn_play);
        this.btn_exit = view.findViewById(R.id.btn_exit);

        this.btn_play.setOnClickListener(this);
        this.btn_exit.setOnClickListener(this);

        return view;
    }

    public static Home_fragment newInstance() {
        Home_fragment fragment = new Home_fragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.btn_play.getId()){
            MainActivity ma = (MainActivity)getActivity();
            ma.changePage(2);
        }
        else{
            MainActivity ma = (MainActivity)getActivity();
            ma.finish();
            System.exit(0);
        }
    }
}
