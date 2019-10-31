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

import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.Presenter.MainPresenter;
import com.example.spaceinvander.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_play extends Fragment{
    private static Fragment_play game;
    private MainPresenter presenter;
    protected TextView score;
    protected ImageView life1,life2,life3,iv_canvas;
    protected ImageView btn_left,btn_right,pause;

    private Thread gameThread;
    private Player player;
    private Meteor meteor;

    protected Canvas mCanvas;
    protected Bitmap bitmap;
    protected Paint paint;
    protected PointF start;

    private int bitmapH, bitmapW;
    private static int SCORE = 0;
    private static int METOEOR_DESTROYED = 0;
    private volatile boolean isGameOver;
    private volatile boolean isHighScore;
    private MainPresenter mp;

    protected  View view;

    public Fragment_play() {
        // Required empty public constructor
    }

    public static Fragment_play createGame(MainPresenter presenter){
        if(game==null){
            game = new Fragment_play();
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

        this.initiateCanvas();

        this.btn_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == event.ACTION_DOWN){
                    player.moveLeft();
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
        this.bitmap = Bitmap.createBitmap(this.bitmapW,this.bitmapH,Bitmap.Config.ARGB_8888);
        Bitmap player = BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);
        this.bitmap = this.bitmap.copy(Bitmap.Config.ARGB_8888,true);
        this.mCanvas = new Canvas(this.bitmap);
        this.player = new Player(this.bitmap.getWidth()/2 - player.getWidth()/2,this.bitmap.getHeight()/2 + player.getHeight()/2,player);
        this.iv_canvas.setImageBitmap(this.bitmap);
        this.resetCanvas();
    }

    public void resetCanvas(){
        this.bitmap.eraseColor(Color.TRANSPARENT);
        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(getResources().getColor(R.color.white),PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);
        this.mCanvas.drawBitmap(player.getMbitmap(),player.getmX(),player.getmY(),paint);
        this.iv_canvas.invalidate();
    }

    public void setBitmapH(int bitmapH) {
        this.bitmapH = bitmapH;
    }

    public void setBitmapW(int bitmapW) {
        this.bitmapW = bitmapW;
    }
}

