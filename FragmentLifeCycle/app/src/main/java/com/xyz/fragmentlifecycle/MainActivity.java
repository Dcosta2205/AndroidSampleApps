package com.xyz.fragmentlifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printLogs("onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        printLogs("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        printLogs("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        printLogs("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        printLogs("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        printLogs("onDestroy");
    }

    private void printLogs(String message) {
        Log.d(TAG, message);
    }
}