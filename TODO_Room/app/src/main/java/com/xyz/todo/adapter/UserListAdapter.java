package com.xyz.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.todo.R;
import com.xyz.todo.viewholder.UserListViewHolder;
import com.xyz.todo.listeners.RecyclerItemClickListener;
import com.xyz.todo.model.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    private List<User> userList;
    private RecyclerItemClickListener listener;

    public UserListAdapter(List<User> studentList, RecyclerItemClickListener listener) {
        this.userList = studentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new UserListViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        User user = userList.get(position);
        holder.setData(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void updateData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
}
