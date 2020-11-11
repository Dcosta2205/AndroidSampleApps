package com.xyz.one_to_one_room.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;

public class DogAndOwner implements Serializable {

    @Embedded
    private Owner owner;

    @Relation(
            parentColumn = "ownerId",
            entityColumn = "dogOwnerId"
    )
    private Dog dog;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Owner getOwner() {
        return owner;
    }

    public Dog getDog() {
        return dog;
    }
}
