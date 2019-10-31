package com.example.spaceinvander.View;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.Presenter.MainPresenter;
import com.example.spaceinvander.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class Fragment_play extends Fragment{
    private int bitmapH, bitmapW;
    private static int SCORE = 0;
    private static int METOEOR_DESTROYED = 0;
    private volatile boolean isGameOver;
    private volatile boolean isHighScore;
    private MainPresenter mp;

    protected  View view;
    protected ThreadLaser threadLaser;
    protected LaserMoveThread laserMoveThread;
    protected ThreadHandler handler;

    protected MainActivity mainActivity;

    public Fragment_play() {
        // Required empty public constructor
    }

    public static Fragment_play createGame(MainPresenter presenter, MainActivity mainActivity){
        if(game==null){
            game = new Fragment_play();
            game.presenter = presenter;
            game.mainActivity = mainActivity;
        }
        return game;
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
        this.pause = view.findViewById(R.id.ib_pause);
        this.iv_canvas = view.findViewById(R.id.iv_layar);
        this.btn_left = view.findViewById(R.id.btn_left);
        this.btn_right = view.findViewById(R.id.btn_right);
 


        this.pause.setOnClickListener(this);
        this.btn_left.setOnClickListener(this);
        this.btn_right.setOnClickListener(this);

        this.handler = new ThreadHandler(this.mainActivity);
        this.lasers = new ArrayList<>();
        this.iv_canvas.setOnTouchListener(this);

        this.gestureListener = new GestureListener();
        this.gestureDetector = new GestureDetector(gestureListener);

        this.mBitmap = Bitmap.createBitmap(this.iv_canvas.getWidth(), this.iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
        this.iv_canvas.setImageBitmap(this.mBitmap);
        this.mCanvas = new Canvas(this.mBitmap);
        this.mCanvas.drawBitmap(mPlayer.getMbitmap(),mPlayer.getmX(), mPlayer.getmY(),this.mPaint);
        return view;
    }

    public static Fragment_play newInstance(int screenX, int screenY) {
        Fragment_play fragment = new Fragment_play(screenX, screenY);
        return fragment;
    }


        this.iv_canvas.invalidate();
    }

    @Override
    public void onClick(View v) {
        this.pause = view.findViewById(R.id.iv_pause);
        this.iv_canvas = view.findViewById(R.id.iv_layar);
        this.btn_left = view.findViewById(R.id.btn_left);
        this.btn_right = view.findViewById(R.id.btn_right);

        this.initiateCanvas();

        this.btn_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == event.ACTION_DOWN){
                    player.moveLeft();
                    System.out.println("kiri");
                    System.out.println(player.getmX());
                    resetCanvas();
                }
                return true;
            }
        });
        this.btn_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==event.ACTION_DOWN){
                    player.moveRight();
                    System.out.println("kanan");
                    System.out.println(player.getmX());
                    resetCanvas();
                }
                return true;
            }
        });
        return view;
    }

    public static Fragment_play newInstance() {
        Fragment_play fragment = new Fragment_play();
        return fragment;
    }

    private void initiateCanvas(){
        this.handler = new ThreadHandler(this.mainActivity);
        System.out.println(this.bitmapW+" "+this.bitmapH);
        this.bitmap = Bitmap.createBitmap(this.bitmapW,this.bitmapH,Bitmap.Config.ARGB_8888);
        Bitmap player = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);
        
        Bitmap laser = BitmapFactory.decodeResource(getResources(), R.drawable.laser);
        this.laser = new Laser(this.bitmapW/2 - player.getWidth()/2,this.bitmapH/2 - 100 + player.getHeight(), laser);
        
        this.bitmap = this.bitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.mCanvas = new Canvas(this.bitmap);

        this.player = new Player(this.bitmapW/2 - player.getWidth()/2,this.bitmapH/2 + player.getHeight(),player,this.bitmapW);
        this.iv_canvas.setImageBitmap(this.bitmap);
        this.threadLaser = new ThreadLaser(this.handler, this.player, laser);
        this.threadLaser.start();

        this.laserMoveThread = new LaserMoveThread(this.handler, this.lasers);
        this.laserMoveThread.start();
        this.resetCanvas();
    }

    public void resetCanvas(){
        this.bitmap.eraseColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        this.mCanvas.drawBitmap(player.getMbitmap(),player.getmX(),player.getmY(),paint);
        this.iv_canvas.invalidate();
    }

    public void setBitmapH(int bitmapH) {
        this.bitmapH = bitmapH;co
    }

    public void setBitmapW(int bitmapW) {
        this.bitmapW = bitmapW;
    }

    public void draw(){

    }

    public void drawLaser(int x, int y){
        Rect rectangle = new Rect(x+10 , y + 350, x - 10, y + 300);
        this.mCanvas.drawRect(rectangle, paint);
    }

    public void setLaser(Laser bullet){
        this.lasers.add(bullet);
        resetCanvas();
        for (int i = 0; i < this.lasers.size(); i++) {
            this.drawLaser((int) this.lasers.get(i).getmX(), (int) this.lasers.get(i).getmY());
        }
    }

    public void setLasers(ArrayList<Laser> bullets){
        this.lasers = bullets;
        resetCanvas();
        for (int i = 0; i < this.lasers.size(); i++) {
            this.drawLaser((int) this.lasers.get(i).getmX(), (int) this.lasers.get(i).getmY());
        }

    }
}

