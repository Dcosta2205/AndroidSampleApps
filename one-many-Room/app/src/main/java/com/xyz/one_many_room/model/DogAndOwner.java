package com.xyz.one_many_room.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class DogAndOwner implements Serializable {

    @Embedded
    private Owner owner;

    @Relation(
            parentColumn = "ownerId",
            entityColumn = "dogOwnerId"
    )
    private List<Dog> dog;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Dog> getDog() {
        return dog;
    }

    public void setDog(List<Dog> dog) {
        this.dog = dog;
    }
}
