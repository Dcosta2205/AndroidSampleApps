package com.xyz.handler_looper_threads_messagequeue;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * @author Lloyd Dcosta
 * This is a thread is used to perform some operations in background
 */
public class BackGroundTask extends Thread {

    private static final String TAG = BackGroundTask.class.getSimpleName();
    public Handler mHandler;
    public Looper looper;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        looper = Looper.myLooper();
        mHandler = new Handler(looper);

        Looper.loop();
        Log.d(TAG, "Outside Loop");
    }

    public void addTaskToQueue(Runnable task) {
        /*
        Handler.post will add the messages to message queue associated with this thread
         */
        mHandler.post(task);
    }
}
