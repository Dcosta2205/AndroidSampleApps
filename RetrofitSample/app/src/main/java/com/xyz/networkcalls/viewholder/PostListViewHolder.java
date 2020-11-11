package com.xyz.networkcalls.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.networkcalls.R;
import com.xyz.networkcalls.model.PostQuery;

public class PostListViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvEmail;
    private TextView mTvTitle;

    public PostListViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvEmail = itemView.findViewById(R.id.tvEmail);
        mTvTitle = itemView.findViewById(R.id.tvTitle);
    }

    public void setData(PostQuery data) {
        mTvTitle.setText(data.getName());
        mTvEmail.setText(data.getEmail());
    }
}
