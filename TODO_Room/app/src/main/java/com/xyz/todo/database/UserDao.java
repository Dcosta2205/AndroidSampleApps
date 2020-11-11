package com.xyz.todo.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.xyz.todo.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("Select * From Users")
    List<User> getAllUsers();

    @Insert
    void insertUser(User user);

    @Query("UPDATE Users SET name =:name, last_name =:last_name Where id =:id")
    void updateFewDetails(String name, String last_name, int id);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM Users Where name =:name")
    List<User> getUserByName(String name);
}
