package com.xyz.one_many_room.interfaces;


import com.xyz.one_many_room.model.DogAndOwner;

public interface RecyclerItemClickListener {

    void onEditDataClicked(int position, DogAndOwner user);

    void onDeleteClicked(int position, DogAndOwner user);
}
