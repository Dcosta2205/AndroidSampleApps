package com.xyz.basicservice;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

/**
 * @author Lloyd Dcosta
 * This thread is used to perform background task here downloading file.
 */
public class BackgroundTask extends HandlerThread {
    public Handler mHandler;
    private HandlerReadyListener listener;

    public BackgroundTask(String name, HandlerReadyListener listener) {
        super(name);
        this.listener = listener;
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Log.d("Lloyd", "Looper prepared");
        mHandler = new Handler(getLooper());
        if (listener != null) {
            listener.onHandlerPrepared();
        }
    }

    /**
     * This method is used to add the task to the message queue of the background thread.
     *
     * @param task task that needs to be performed.
     */
    public void addTaskToMessageQueue(Runnable task) {
        mHandler.post(task);
    }
}
