package com.example.spaceinvander.View;


import android.annotation.SuppressLint;
import android.content.Context;
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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.Toast;

import com.example.spaceinvander.Model.Laser;
import com.example.spaceinvander.Model.Meteor;
import com.example.spaceinvander.Model.Player;
import com.example.spaceinvander.Presenter.MainPresenter;
import com.example.spaceinvander.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_play extends Fragment implements SensorEventListener {
    private static Fragment_play game;
    private Context context;
    private MainPresenter presenter;
    private static double sensorValue;

    protected TextView score;
    protected ImageView life1,life2,life3,iv_canvas;
    protected ImageView btn_left,btn_right,pause;
    protected Button btn_sensor;

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
    private boolean isClicked = false;

    private Paint paint;
    protected Paint paintLaser;
    protected Paint paintEnemyLaser;

    protected ColorFilter putih;
    protected ColorFilter merah;

    private MainPresenter mp;
    protected MainActivity mainActivity;
    protected ThreadEnemy threadEnemy;
    protected ThreadLaser threadLaser;
    protected ThreadLaserMove threadLaserMove;
    protected ThreadEnemyLaser threadEnemyLaser;
    protected ThreadEnemyLaserMove threadEnemyLaserMove;
    protected ThreadHandler threadHandler;
    protected ArrayList<Laser> lasers = new ArrayList<>();
    protected ArrayList<Laser> enemyLasers = new ArrayList<>();

//    Sensor
protected Sensor accelerometer;
    protected Sensor magnetometer;
    protected float[] accelerometerR = new float[3];
    protected float[] magnetometerR = new float[3];
    private static final float VALUE_DRIFT = 0.05f;
    protected SensorManager manager;
    protected float temp = 4;

    protected  View view;

    public Fragment_play() {
        // Required empty public constructor
    }

    public static Fragment_play createGame(MainPresenter presenter,MainActivity mainActivity,Context context){
        if(game==null){
            game = new Fragment_play();
            game.mainActivity = mainActivity;
            game.presenter = presenter;
            game.context = context;
        }
        return game;
    }

    @SuppressLint("ClickableViewAccessibility")
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
        this.btn_sensor = view.findViewById(R.id.btn_sensor);

        this.manager = (SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = this.manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

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

        this.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!threadLaser.getPause()){
                    Toast toast = Toast.makeText(getContext(),"Pause",Toast.LENGTH_SHORT);
                    toast.show();
                    threadLaser.setPause(true);
                    threadLaser.pause();
                }
                else{
                    Toast toast = Toast.makeText(getContext(),"Resume",Toast.LENGTH_SHORT);
                    threadLaser.setPause(false);
                    threadLaser.resume();
                }
            }
        });

        this.btn_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(sensorValue);
                if(!isClicked){
                    btn_sensor.setText("BUTTON MODE");
                    btn_left.setVisibility(View.INVISIBLE);
                    btn_right.setVisibility(View.INVISIBLE);
                    Toast toast = Toast.makeText(getContext(),"Sensor mode active",Toast.LENGTH_SHORT);
                    toast.show();
                    isClicked = true;
                }
                else{
                    btn_sensor.setText("SENSOR MODE");
                    btn_right.setVisibility(View.VISIBLE);
                    btn_left.setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(getContext(),"Button mode active",Toast.LENGTH_SHORT);
                    toast.show();
                    isClicked = false;
                }
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

        this.threadLaserMove = new ThreadLaserMove(this.threadHandler, lasers,this.musuh);
        this.threadLaserMove.start();

        this.threadEnemy = new ThreadEnemy(this.musuh);
        this.threadEnemy.start();

        this.threadEnemyLaser = new ThreadEnemyLaser(this.threadHandler, this.musuh);
        this.threadEnemyLaser.start();

        this.threadEnemyLaserMove = new ThreadEnemyLaserMove(this.threadHandler, this.enemyLasers, this.player);
        this.threadEnemyLaserMove.start();

        this.resetCanvas();
    }

    public void resetCanvas(){
        this.bitmap.eraseColor(Color.TRANSPARENT);

        this.paint = new Paint();
        this.paintLaser = new Paint();
        this.paintEnemyLaser = new Paint();

        this.putih = new PorterDuffColorFilter(getResources().getColor(R.color.white),PorterDuff.Mode.SRC_IN);
        this.merah = new PorterDuffColorFilter(getResources().getColor(R.color.red),PorterDuff.Mode.SRC_IN);

        this.paintLaser.setColorFilter(putih);
        this.paintEnemyLaser.setColorFilter(merah);

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
        Rect laser = new Rect(x+135, y-80, x+165, y-10);
        this.mCanvas.drawRect(laser, paintLaser);
    }

    public void setLaser(Laser laser) {
        int bitMapW = this.bitmapW;
        this.lasers.add(laser);
        resetCanvas();
        for (int i = 0; i < this.lasers.size(); i++) {
            this.drawLaser((int)this.lasers.get(i).getmX(), (int) this.lasers.get(i).getmY());
        }
    }

    public void setLasers(ArrayList<Laser> lasers){
        this.lasers = lasers;
        resetCanvas();
        for (int i = 0; i < this.lasers.size(); i++) {
            this.drawLaser((int)this.lasers.get(i).getmX(), (int)this.lasers.get(i).getmY());
        }
    }

    public void drawEnemyLaser(int x, int y){
        Rect laser = new Rect(x+135, y+200, x+165, y+250);
        this.mCanvas.drawRect(laser, paintEnemyLaser);
    }

    public void setEnemyLaser(Laser laser) {
        this.enemyLasers.add(laser);
        resetCanvas();
        for (int i = 0; i < this.enemyLasers.size(); i++) {
            this.drawEnemyLaser((int)this.enemyLasers.get(i).getmX(), (int) this.enemyLasers.get(i).getmY());
        }
    }

    public void setEnemyLasers(ArrayList<Laser> lasers){
        this.enemyLasers = lasers;
        resetCanvas();
        for (int i = 0; i < this.enemyLasers.size(); i++) {
            this.drawEnemyLaser((int)this.enemyLasers.get(i).getmX(), (int)this.enemyLasers.get(i).getmY());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(isClicked){
            int sensorType = this.accelerometer.getType();
            switch (sensorType){
                case Sensor.TYPE_ACCELEROMETER:
                    temp = event.values[0];
                    break;
            }
            moveSensor();
        }
        System.out.println(temp);
    }

    public void moveSensor(){
        if(temp > 1){
            System.out.printf("kiri");
            this.player.moveLeftSensor();
            resetCanvas();
        }
        else if(temp < 1){
            System.out.println("kanan");
            this.player.moveRightSensor();
            resetCanvas();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onStart(){
        super.onStart();
        this.initiateCanvas();
        if(this.accelerometer!=null){
            this.manager.registerListener(this, this.accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void onStop(){
        super.onStop();
        this.manager.unregisterListener(this);
    }
}

