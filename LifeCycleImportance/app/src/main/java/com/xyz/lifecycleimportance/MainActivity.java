package com.xyz.lifecycleimportance;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * In this example, if the user clicks play button and goes to background the music will still continue.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnPlay;
    private MediaPlayer mediaPlayer;
    private Button mBtnStop;

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
        } else if (view.getId() == R.id.btnStop && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
}