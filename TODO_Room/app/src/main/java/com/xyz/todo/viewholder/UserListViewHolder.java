package com.xyz.todo.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.todo.R;
import com.xyz.todo.listeners.RecyclerItemClickListener;
import com.xyz.todo.model.User;


public class UserListViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemClickListener listener;
    private TextView mTvName;
    private TextView mTvLastName;
    private ImageView mIvEdit;
    private ImageView mIvDelete;

    public UserListViewHolder(@NonNull View itemView, RecyclerItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvName = itemView.findViewById(R.id.tvName);
        mTvLastName = itemView.findViewById(R.id.tvAddress);
        mIvEdit = itemView.findViewById(R.id.ivEdit);
        mIvDelete = itemView.findViewById(R.id.ivDelete);
    }

    public void setData(final User user) {
        mTvLastName.setText(user.getLastName());
        mTvName.setText(user.getName());

        mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditDataClicked(getAdapterPosition(), user);
            }
        });

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(getAdapterPosition(), user);
            }
        });
    }
}
