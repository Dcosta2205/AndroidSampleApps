package com.lloyd.cardsandanimation;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTvHelloWorld;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // change the id if you want to view the cardview layout (use R.layout.cardview_sample)
        mTvHelloWorld = findViewById(R.id.tvHelloWorld);
        animation = AnimationUtils.loadAnimation(this, R.anim.fadein); // change the anim id accordingly to view different kind of animations
        mTvHelloWorld.startAnimation(animation); // Start the animation on the textview
    }
}