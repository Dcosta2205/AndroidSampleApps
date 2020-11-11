package com.xyz.one_to_one_room.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.xyz.one_to_one_room.model.Dog;
import com.xyz.one_to_one_room.model.DogAndOwner;
import com.xyz.one_to_one_room.model.Owner;

import java.util.List;

@Dao
public interface OwnerDogDao {

    @Insert
    void insertOwner(Owner owner);

    @Insert
    void insertDog(Dog dog);

    @Transaction
    @Query("Select * From owner")
    List<DogAndOwner> getDogsWithOwner();

    @Delete
    void deleteOwner(Owner owner);

    @Query("Update owner SET OwnerName=:ownerName where ownerId =:ownerId")
    void updateOwnerDetails(String ownerId, String ownerName);
}
