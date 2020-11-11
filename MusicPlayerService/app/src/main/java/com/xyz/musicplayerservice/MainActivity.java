package com.xyz.musicplayerservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStartMusic;
    private Button mBtnStopMusic;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnStartMusic = findViewById(R.id.btnStartMusic);
        mBtnStopMusic = findViewById(R.id.btnStopMusic);
        mBtnStartMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MusicService.class);
                startService(intent);
                MusicService.enqueueWork(MainActivity.this, MusicService.class, 1, intent);
            }
        });

        mBtnStopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}