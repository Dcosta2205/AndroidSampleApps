package com.xyz.handler_looper_threads_messagequeue;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lloyd Dcosta
 * This activity is used to start 2 threads and add the task to both the task and perform the operations
 * parallely
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnStart;
    private Button mBtnStop;
    private Button mBtnTaskOne;
    private Button mBtnTaskTwo;
    private Button mBtnHandlerThread;
    private Button mBtnStopHandlerThread;
    private BackGroundTask looperThread;
    private ThreadWithLooper threadWithLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnStart = findViewById(R.id.btnStart);
        mBtnStop = findViewById(R.id.btnStop);
        mBtnTaskOne = findViewById(R.id.btnTaskOne);
        mBtnTaskTwo = findViewById(R.id.btnTaskTwo);
        mBtnHandlerThread = findViewById(R.id.btnStartHandlerThread);
        mBtnStopHandlerThread = findViewById(R.id.btnStopHandlerThread);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnTaskOne.setOnClickListener(this);
        mBtnTaskTwo.setOnClickListener(this);
        looperThread = new BackGroundTask();
        mBtnHandlerThread.setOnClickListener(this);
        mBtnStopHandlerThread.setOnClickListener(this);
        threadWithLooper = new ThreadWithLooper("Lloyd Thread");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnStart:
                looperThread.start();
                break;
            case R.id.btnStop:
                looperThread.looper.quit();
                break;
            case R.id.btnStartHandlerThread:
                threadWithLooper.start();
                break;
            case R.id.btnStopHandlerThread:
                break;
            case R.id.btnTaskOne:
                looperThread.addTaskToQueue(addTaskToQueue());
                break;
            case R.id.btnTaskTwo:
                threadWithLooper.addTaskToQueue(addTaskToQueue());
                break;
        }
    }

    private Runnable addTaskToQueue() {
        return new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread name " + Thread.currentThread().getName());
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, i + "");
                    SystemClock.sleep(2000);
                }
            }
        };
    }
}