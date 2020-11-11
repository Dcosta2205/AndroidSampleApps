package com.xyz.todo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.xyz.todo.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
