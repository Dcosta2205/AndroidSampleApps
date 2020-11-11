package com.xyz.one_to_one_room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.xyz.one_to_one_room.model.Dog;
import com.xyz.one_to_one_room.model.Owner;

@Database(entities = {Owner.class, Dog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OwnerDogDao ownerDogDao();
}
