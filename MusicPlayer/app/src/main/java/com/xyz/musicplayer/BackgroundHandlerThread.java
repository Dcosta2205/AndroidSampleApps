package com.xyz.musicplayer;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * @author Lloyd Dcosta
 * This is a background thread which is used to handle the music playing in the backgroud.
 */
public class BackgroundHandlerThread extends HandlerThread {

    private Handler mHandler;
    private LooperPreparedListener listener;

    public BackgroundHandlerThread(String name, LooperPreparedListener looperPreparedListener) {
        super(name);
        listener = looperPreparedListener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper());
        if (listener != null) {
            listener.onLooperReady();
        }
    }

    public void addTaskToQueue(Runnable task) {
        mHandler.post(task);
    }
}
