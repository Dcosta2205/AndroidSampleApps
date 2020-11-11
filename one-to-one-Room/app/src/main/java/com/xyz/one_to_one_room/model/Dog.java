package com.xyz.one_to_one_room.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Dogs",
        foreignKeys = @ForeignKey(
                entity = Owner.class,
                parentColumns = "ownerId",
                childColumns = "dogOwnerId",
                onDelete = CASCADE
        ))
public class Dog {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dogId")
    private int dogId;

    @ColumnInfo(name = "dogOwnerId")
    private String dogOwnerId;

    @ColumnInfo(name = "DogName")
    private String dogName;

    @ColumnInfo(name = "DogBreed")
    private String dogBreed;

    public Dog(String dogName, String dogBreed, String dogOwnerId) {
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogOwnerId = dogOwnerId;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getDogName() {
        return dogName;
    }

    public String getDogOwnerId() {
        return dogOwnerId;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogId=" + dogId +
                ", dogOwnerId=" + dogOwnerId +
                ", dogName='" + dogName + '\'' +
                ", dogBreed='" + dogBreed + '\'' +
                '}';
    }
}
