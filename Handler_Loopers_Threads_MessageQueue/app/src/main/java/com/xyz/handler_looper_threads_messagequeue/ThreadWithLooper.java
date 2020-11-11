package com.xyz.handler_looper_threads_messagequeue;

import android.os.Handler;
import android.os.HandlerThread;

public class ThreadWithLooper extends HandlerThread {

    private Handler mHandler;

    /*
    This name will be the thread name
     */
    public ThreadWithLooper(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper());
    }

    public void addTaskToQueue(Runnable task) {
        /*
        Handler.post will add the messages to message queue associated with this thread
         */
        mHandler.post(task);
    }
}
