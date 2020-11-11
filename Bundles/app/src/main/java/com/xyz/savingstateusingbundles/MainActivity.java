package com.xyz.savingstateusingbundles;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity demonstrates how to restore the data if the activity is recreated because of screen orientation.
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTvRestoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        /*
        SavedInstanceState will have the value if the values are saved in the onSavedInstanceState method.
         */
        if (savedInstanceState != null) {
            mTvRestoreData.setText(savedInstanceState.getString("message"));
        } else {
            /*
            This value will be displayed initially when the savedInstanceState is null
             */
            mTvRestoreData.setText(R.string.retain_text);
        }
    }

    private void initViews() {
        mTvRestoreData = findViewById(R.id.tvRestore);
    }

    /**
     * This method is called between onPause() and onStop() when the activity goes background
     *
     * @param outState the data that has to be saved can be saved in this bundle
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /*
        This message will be loaded when the activity is re-created when the orientation changes
         */
        outState.putString("message", "This is the message to be reloaded");
        Log.d("Lloyd", "onSaveInstanceState");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lloyd", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lloyd", "onResume");
    }

    /**
     * This method is called between onStart() and onResume() when the activity is recreated because of screen rotation
     *
     * @param savedInstanceState bundle that is saved in onSavedInstanceState()
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("Lloyd", "onRestoreInstanceState");
        mTvRestoreData.setText(savedInstanceState.getString("message"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lloyd", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lloyd", "onStop");
    }
}