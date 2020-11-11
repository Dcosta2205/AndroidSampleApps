package com.xyz.one_many_room.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Owner")
public class Owner {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ownerId")
    private String ownerId;

    @ColumnInfo(name = "OwnerName")
    private String ownerName;

    @Ignore
    public Owner(String ownerName) {
        this(UUID.randomUUID().toString(), ownerName);
    }

    public Owner(String ownerId, String ownerName) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
