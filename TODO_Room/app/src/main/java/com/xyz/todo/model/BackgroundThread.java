package com.xyz.todo.model;

import android.os.Handler;
import android.os.HandlerThread;

import com.xyz.todo.listeners.LooperPreparedListener;

public class BackgroundThread extends HandlerThread {

    public Handler mHandler;
    private LooperPreparedListener looperPreparedListener;

    public BackgroundThread(String name, LooperPreparedListener looperPreparedListener) {
        super(name);
        this.looperPreparedListener = looperPreparedListener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper());
        looperPreparedListener.onLooperPrepared();
    }

    public void addTaskToQueue(Runnable task) {
        mHandler.post(task);
    }
}
