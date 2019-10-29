package com.example.spaceinvander.View;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spaceinvander.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_play extends Fragment implements View.OnClickListener, View.OnTouchListener{

    protected TextView textScore,score;
    protected ImageView life1,life2,life3,iv_canvas,pesawat,meteor;
    protected Button pause,btn_left,btn_right;

    protected Canvas mCanvas;
    protected Bitmap bitmap;

    protected Paint paint;

    protected PointF start;

    protected GestureDetector gestureDetector;
    protected GestureListener gestureListener;

    protected  View view;

    public Fragment_play() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_play, container, false);
//      find view by id
        this.textScore = view.findViewById(R.id.tv_score);
        this.score = view.findViewById(R.id.tv_angka_score);
        this.life1 = view.findViewById(R.id.iv_life1);
        this.life2 = view.findViewById(R.id.iv_life2);
        this.life3 = view.findViewById(R.id.iv_life3);
        this.pause = view.findViewById(R.id.iv_pause);
        this.iv_canvas = view.findViewById(R.id.iv_layar);
        this.btn_left = view.findViewById(R.id.btn_left);
        this.btn_right = view.findViewById(R.id.btn_right);

        this.pause.setOnClickListener(this);
        this.btn_left.setOnClickListener(this);
        this.btn_right.setOnClickListener(this);

        this.iv_canvas.setOnTouchListener(this);

        this.gestureListener = new GestureListener();
        this.gestureDetector = new GestureDetector(gestureListener);
        return view;
    }

    public static Fragment_play newInstance() {
        Fragment_play fragment = new Fragment_play();
//        this.bitmap = Bitmap.createBitmap(this.iv_canvas.getWidth(), this.iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
//
//        this.iv_canvas.setImageBitmap(this.bitmap);
//
//        this.mCanvas = new Canvas(this.bitmap);
//
//        this.paint = new Paint()
//
//        this.mCanvas.drawRect(20,);

        return fragment;
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{

    }
}

