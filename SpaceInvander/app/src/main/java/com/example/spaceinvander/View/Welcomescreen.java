package com.example.spaceinvander.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.spaceinvander.R;

public class Welcomescreen extends AppCompatActivity {
    protected int waktu_delay = 1000;//3 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcomescreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },waktu_delay);
    }
}
