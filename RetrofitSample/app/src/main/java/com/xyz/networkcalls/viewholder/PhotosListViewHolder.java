package com.xyz.networkcalls.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xyz.networkcalls.R;
import com.xyz.networkcalls.model.PhotosResponse;

public class PhotosListViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvTitle;
    private ImageView mIvThumbnail;

    public PhotosListViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvTitle = itemView.findViewById(R.id.tvTitle);
        mIvThumbnail = itemView.findViewById(R.id.ivThumbnail);
    }

    public void setData(PhotosResponse response) {
        mTvTitle.setText(response.getTitle());
        Glide.with(mIvThumbnail.getContext())
                .load("https://avatars0.githubusercontent.com/u/1687294?v=4")
                .into(mIvThumbnail);
    }
}
