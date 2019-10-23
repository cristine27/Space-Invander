package com.example.spaceinvander.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spaceinvander.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_play extends Fragment {


    public Fragment_play() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_play, container, false);
    }

    public static Fragment_play newInstance() {
        Fragment_play fragment = new Fragment_play();
        return fragment;
    }
}
