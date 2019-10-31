package com.example.spaceinvander.View;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
<<<<<<< Updated upstream
=======
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
>>>>>>> Stashed changes
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_play extends Fragment implements View.OnClickListener, View.OnTouchListener{

    protected TextView textScore,score;
    protected ImageView life1,life2,life3,iv_canvas,pesawat,meteor;
    protected ImageView pause;
    protected ImageButton btn_left,btn_right;

    protected Laser laser;
    protected ArrayList<Laser> lasers;


    protected Canvas mCanvas;
    protected Bitmap mBitmap;
    protected Paint mPaint;

    protected Player mPlayer;

    protected GestureDetector gestureDetector;
    protected GestureListener gestureListener;

    protected  View view;

<<<<<<< Updated upstream
    protected int screenX;
    protected int screenY;

    public Fragment_play(int screenX, int screenY) {
=======
    protected ThreadLaser threadLaser;
    protected LaserMoveThread laserMoveThread;
    protected ThreadHandler handler;

    protected MainActivity mainActivity;

    public Fragment_play() {
>>>>>>> Stashed changes
        // Required empty public constructor
        this.screenX = screenX;
        this.screenY = screenY;
        this.mPaint = new Paint();
    }

<<<<<<< Updated upstream
=======
    public static Fragment_play createGame(MainPresenter presenter, MainActivity mainActivity){
        if(game==null){
            game = new Fragment_play();
            game.presenter = presenter;
            game.mainActivity = mainActivity;
        }
        return game;
    }
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
        this.mPlayer = new Player(getContext(), screenX, screenY);


        this.pause.setOnClickListener(this);
        this.btn_left.setOnClickListener(this);
        this.btn_right.setOnClickListener(this);
=======
        this.handler = new ThreadHandler(this.mainActivity);
        this.lasers = new ArrayList<>();
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
    public void moveLeft(float speed) {
        mPlayer.moveLeft(speed);
    }

    public void moveRight(float speed) {
        mPlayer.moveRight(speed);
    }

    public void reset(){
        this.mCanvas = new Canvas(this.mBitmap);
=======
    private void initiateCanvas(){
        this.handler = new ThreadHandler(this.mainActivity);

        System.out.println(this.bitmapW+" "+this.bitmapH);
        this.bitmap = Bitmap.createBitmap(this.bitmapW,this.bitmapH,Bitmap.Config.ARGB_8888);
        Bitmap player = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);

        this.bitmap = this.bitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.mCanvas = new Canvas(this.bitmap);

        this.player = new Player(this.bitmapW/2 - player.getWidth()/2,this.bitmapH/2 + player.getHeight(),player,this.bitmapW);
        this.iv_canvas.setImageBitmap(this.bitmap);

        Bitmap laser = BitmapFactory.decodeResource(getResources(), R.drawable.laser);
        this.laser = new Laser(this.bitmapW/2 - player.getWidth()/2,this.bitmapH/2 - 100 + player.getHeight(), laser);

        this.iv_canvas.setImageBitmap(this.bitmap);

        this.threadLaser = new ThreadLaser(this.handler, this.player, laser);
        this.threadLaser.start();

        this.laserMoveThread = new LaserMoveThread(this.handler, this.lasers);
        this.laserMoveThread.start();

        this.resetCanvas();
    }

    public void resetCanvas(){
        this.bitmap.eraseColor(Color.TRANSPARENT);
        paint = new Paint();
//        ColorFilter filter = new PorterDuffColorFilter(getResources().getColor(R.color.white),PorterDuff.Mode.SRC_IN);
//        paint.setColorFilter(filter);

        this.mCanvas.drawBitmap(laser.getMbitmap(),laser.getmX(),laser.getmY(),paint);
        this.mCanvas.drawBitmap(player.getMbitmap(),player.getmX(),player.getmY(),paint);

>>>>>>> Stashed changes
        this.iv_canvas.invalidate();
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

