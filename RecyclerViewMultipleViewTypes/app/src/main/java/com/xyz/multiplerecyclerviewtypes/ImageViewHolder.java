package com.xyz.multiplerecyclerviewtypes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ItemClickListener itemClickListener;
    private TextView mTvImageTitle;
    private ImageView mIvScenery;

    public ImageViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mIvScenery = itemView.findViewById(R.id.ivScenery);
        mTvImageTitle = itemView.findViewById(R.id.tvImageTitle);
    }

    public void setData(Model model) {
        mTvImageTitle.setText(model.getContent());
        mIvScenery.setImageResource(model.getResId());
    }
}
