package com.example.spaceinvander.View;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
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
    private static Fragment_play game;
    private MainPresenter presenter;
    protected TextView score;
    protected ImageView life1,life2,life3,iv_canvas;
    protected ImageView btn_left,btn_right,pause;

    private Player player;
    private Meteor musuh;

    protected Canvas mCanvas;
    protected Bitmap bitmap;
    protected Bitmap bitmapE;
    private int bitmapH, bitmapW;
    private static int SCORE = 0;
    private static int METOEOR_DESTROYED = 0;
    private volatile boolean isGameOver;
    private volatile boolean isHighScore;

    private Paint paint;
    private MainPresenter mp;
    protected MainActivity mainActivity;
    protected ThreadEnemy threadEnemy;
    protected ThreadLaser threadLaser;
    protected ThreadLaserMove threadLaserMove;
    protected ThreadHandler threadHandler;
    protected ArrayList<Laser> lasers = new ArrayList<>();

    protected  View view;

    public Fragment_play() {
        // Required empty public constructor
    }

    public static Fragment_play createGame(MainPresenter presenter,MainActivity mainActivity){
        if(game==null){
            game = new Fragment_play();
            game.mainActivity = mainActivity;
            game.presenter = presenter;
        }
        return game;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_play, container, false);
        this.score = view.findViewById(R.id.tv_angka_score);
        this.life1 = view.findViewById(R.id.iv_life1);
        this.life2 = view.findViewById(R.id.iv_life2);
        this.life3 = view.findViewById(R.id.iv_life3);
        this.pause = view.findViewById(R.id.iv_pause);
        this.iv_canvas = view.findViewById(R.id.iv_layar);
        this.btn_left = view.findViewById(R.id.btn_left);
        this.btn_right = view.findViewById(R.id.btn_right);

        this.paint = new Paint();

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
        System.out.println(this.bitmapW+" "+this.bitmapH);

        this.threadHandler = new ThreadHandler(this.mainActivity);

        this.bitmap = Bitmap.createBitmap(this.bitmapW,this.bitmapH,Bitmap.Config.ARGB_8888);
        this.bitmapE = Bitmap.createBitmap(this.bitmapW,this.bitmapH,Bitmap.Config.ARGB_8888);
        Bitmap player = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);
        Bitmap musuh = BitmapFactory.decodeResource(getResources(),R.drawable.enemy);
        this.bitmap = this.bitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.bitmapE = this.bitmapE.copy(Bitmap.Config.ARGB_8888,true);
        this.mCanvas = new Canvas(this.bitmap);

        this.player = new Player(this.bitmapW/2 - player.getWidth()/2,this.bitmapH/2 + player.getHeight()*2,player,this.bitmapW);
        this.musuh = new Meteor(this.bitmapW/2-musuh.getWidth()/2,musuh.getHeight(),musuh,this.bitmapW,this.bitmapH);
        this.iv_canvas.setImageBitmap(this.bitmap);

        this.threadLaser = new ThreadLaser(this.threadHandler, this.player);
        this.threadLaser.start();

        this.threadLaserMove = new ThreadLaserMove(this.threadHandler, lasers);
        this.threadLaserMove.start();

        this.threadEnemy = new ThreadEnemy(this.musuh);
        this.threadEnemy.start();

        this.resetCanvas();
    }

    public void resetCanvas(){
        this.bitmap.eraseColor(Color.TRANSPARENT);
        this.paint = new Paint();
        this.mCanvas.drawBitmap(player.getMbitmap(),player.getmX(),player.getmY(),paint);
        this.mCanvas.drawBitmap(musuh.getMbitmap(),musuh.getmX(),musuh.getmY(),paint);
        this.iv_canvas.invalidate();
    }

    public void setBitmapH(int bitmapH) {
        this.bitmapH = bitmapH;
    }

    public void setBitmapW(int bitmapW) {
        this.bitmapW = bitmapW;
    }

    public void drawLaser(int x, int y){
        Rect laser = new Rect(x+10, y+350, x-10, y+300);
        this.mCanvas.drawRect(laser, paint);
    }

    public void setLaser(Laser laser) {
        int bitMapW = this.bitmapW;
        this.lasers.add(laser);
        resetCanvas();
        for (int i = 0; i < this.lasers.size(); i++) {
            this.drawLaser((int) (bitMapW-this.lasers.get(i).getmX()), (int) this.lasers.get(i).getmY());
        }
    }

    public void setLasers(ArrayList<Laser> lasers){
        this.lasers = lasers;
        resetCanvas();
        for (int i = 0; i < this.lasers.size(); i++) {
            this.drawLaser((int)this.lasers.get(i).getmX(), (int)this.lasers.get(i).getmY());
        }

    }
}

