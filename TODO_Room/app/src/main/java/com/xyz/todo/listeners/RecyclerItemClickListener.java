package com.xyz.todo.listeners;


import com.xyz.todo.model.User;

public interface RecyclerItemClickListener {

    void onEditDataClicked(int position, User user);

    void onDeleteClicked(int position, User user);
}
