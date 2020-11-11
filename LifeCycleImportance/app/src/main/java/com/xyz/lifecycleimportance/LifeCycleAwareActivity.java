package com.xyz.lifecycleimportance;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class describes the importance of activity life cycle methods i.e when the user goes to
 * background and when the user comes back music resumes
 */
public class LifeCycleAwareActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnPlay;
    private MediaPlayer mediaPlayer;
    private Button mBtnStop;
    private boolean isPlayClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnPlay = findViewById(R.id.btnPlay);
        mBtnStop = findViewById(R.id.btnStop);
        mediaPlayer = MediaPlayer.create(this, R.raw.shape_of_you);
        mBtnStop.setOnClickListener(this);
        mBtnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPlay) {
            mediaPlayer.start();
            isPlayClicked = true;
        } else if (view.getId() == R.id.btnStop && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlayClicked = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPlayClicked) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }
}