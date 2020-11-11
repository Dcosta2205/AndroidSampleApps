package com.xyz.sqlitedatabase.application;

import android.app.Application;

import com.xyz.sqlitedatabase.database.DBManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.getInstance(this).open();
    }

}
