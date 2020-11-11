package com.xyz.one_many_room.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient databaseClient;
    private Context context;
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Owner_Dog").build();
    }

    public static DatabaseClient getDatabaseClient(Context context) {
        if (databaseClient == null) {
            databaseClient = new DatabaseClient(context);
        }

        return databaseClient;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
