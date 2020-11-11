package com.xyz.one_to_one_room.model;

import android.os.Handler;
import android.os.HandlerThread;

import com.xyz.one_to_one_room.interfaces.LooperPreparedListener;

public class BackgroundThread extends HandlerThread {

    private LooperPreparedListener looperPreparedListener;
    private Handler mHandler;

    public BackgroundThread(String name, LooperPreparedListener looperPreparedListener) {
        super(name);
        this.looperPreparedListener = looperPreparedListener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper());
        looperPreparedListener.looperPreparedListener();
    }

    public void addTaskToMessageQueue(Runnable task) {
        mHandler.post(task);
    }
}
