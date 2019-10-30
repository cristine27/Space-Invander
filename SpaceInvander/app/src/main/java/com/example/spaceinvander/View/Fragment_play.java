package com.example.spaceinvander.View;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.R;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_play extends Fragment implements View.OnClickListener, View.OnTouchListener, Runnable{

    protected TextView textScore;
    protected TextView score;
    protected ImageView life1;
    protected ImageView life2;
    protected ImageView life3;

    protected ImageView iv_canvas;

    protected ImageButton pause;

    protected Canvas mCanvas;
    protected Bitmap bitmap;

    protected Paint paint;

    protected PointF start;

    protected GestureDetector gestureDetector;
    protected GestureListener gestureListener;

    protected  View view;

    protected int x, y;

    protected Player player;
    protected List<Meteor> mMeteor;

    protected Thread myThread;

    protected boolean playing = false;
    protected boolean gameOver = false;
    protected int mCounter = 0;

    public Fragment_play(int x, int y ) {
        // Required empty public constructor
        this.x = x;
        this.y = y;;
    }

    public static Fragment_play newInstance(int x, int y) {
        Fragment_play fragment = new Fragment_play(x, y);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_play, container, false);

        this.textScore = view.findViewById(R.id.tv_score);
        this.score = view.findViewById(R.id.tv_angka_score);
        this.life1 = view.findViewById(R.id.iv_life1);
        this.life2 = view.findViewById(R.id.iv_life2);
        this.life3 = view.findViewById(R.id.iv_life3);
        this.pause = view.findViewById(R.id.ib_pause);
        this.iv_canvas = view.findViewById(R.id.iv_layar);

        this.pause.setOnClickListener(this);
        this.iv_canvas.setOnTouchListener(this);

        this.gestureListener = new GestureListener();
//        this.gestureDetector = new GestureDetector(gestureListener);

        this.mMeteor = new ArrayList<>();

        this.bitmap = Bitmap.createBitmap(300, 500, Bitmap.Config.ARGB_8888);

        this.iv_canvas.setImageBitmap(this.bitmap);

        this.mCanvas = new Canvas(this.bitmap);

        this.paint = new Paint();
        this.paint.setColor(Color.WHITE);

        //iv_canvas kembaliin nilai 0

        return view;
    }

    @Override
    public void onClick(View v) {
//        int tengahX = this.iv_canvas.getWidth()/2/2/2;
//        System.out.println(tengahX);
//        this.mCanvas.drawRect(tengahX-20,450,tengahX+30,500,this.paint);
//        Log.d("width", "" + this.view.getWidth());
//        this.iv_canvas.invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void run() {
        while(playing){
            if(!gameOver){
                update();
            }
        }
    }

    public void update() {
        player.update();
        if (mCounter % 200 == 0) {
            player.fire();
        }

        for (Meteor m : mMeteor) {
            m.update();

            if (Rect.intersects(m.getCekCollision(), player.getmCekCollision())) {
                m.destroy();
                gameOver = true;
//                if (SCORE>mSP.getHighScore()){
//                    mNewHighScore = true;
//                    mSP.saveHighScore(SCORE, METEOR_DESTROYED, ENEMY_DESTROYED);
//                }
            }

//            for (Laser l : player.getLasers()) {
//                if (Rect.intersects(m.getCollision(), l.getCollision())) {
//                    m.hit();
//                    l.destroy();
//                }
//            }
        }

        boolean deleting = true;
        while (deleting) {
            if (mMeteor.size() != 0) {
                if (mMeteor.get(0).getmY() > this.y) {
                    mMeteor.remove(0);
                }
            }

            if (mMeteor.size() == 0 || mMeteor.get(0).getmY() <= this.y) {
                deleting = false;
            }
        }
        if (mCounter % 1000 == 0) {
            mMeteor.add(new Meteor(getContext(), this.x, this.y));
        }

//        for (Enemy e : mEnemies) {
//            e.update();
//            if (Rect.intersects(e.getCollision(), mPlayer.getCollision())) {
//                e.destroy();
//                mIsGameOver = true;
//                if (SCORE>=mSP.getHighScore()){
//                    mSP.saveHighScore(SCORE, METEOR_DESTROYED, ENEMY_DESTROYED);
//                }
//            }
//
//            for (Laser l : mPlayer.getLasers()) {
//                if (Rect.intersects(e.getCollision(), l.getCollision())) {
//                    e.hit();
//                    l.destroy();
//                }
//            }
//        }
//        deleting = true;
//        while (deleting) {
//            if (mEnemies.size() != 0) {
//                if (mEnemies.get(0).getY() > mScreenSizeY) {
//                    mEnemies.remove(0);
//                }
//            }
//
//            if (mEnemies.size() == 0 || mEnemies.get(0).getY() <= mScreenSizeY) {
//                deleting = false;
//            }
//        }
//        if (mCounter % 2000 == 0) {
//            mEnemies.add(new Enemy(getContext(), mScreenSizeX, mScreenSizeY, mSoundPlayer));
//        }
//
//        for (Star s : mStars) {
//            s.update();
//        }
//        deleting = true;
//        while (deleting) {
//            if (mStars.size() != 0) {
//                if (mStars.get(0).getY() > mScreenSizeY) {
//                    mStars.remove(0);
//                }
//            }
//
//            if (mStars.size() == 0 || mStars.get(0).getY() <= mScreenSizeY) {
//                deleting = false;
//            }
//        }
//
//        if (mCounter % 250 == 0) {
//            Random random = new Random();
//            for (int i = 0; i < random.nextInt(3) + 1; i++) {
//                mStars.add(new Star(getContext(), mScreenSizeX, mScreenSizeY, false));
//            }
//
//        }
//

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{

    }
}

