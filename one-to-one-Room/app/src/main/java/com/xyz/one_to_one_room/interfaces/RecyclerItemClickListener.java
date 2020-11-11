package com.xyz.one_to_one_room.interfaces;

import com.xyz.one_to_one_room.model.DogAndOwner;

public interface RecyclerItemClickListener {

    void onEditDataClicked(int position, DogAndOwner user);

    void onDeleteClicked(int position, DogAndOwner user);
}
