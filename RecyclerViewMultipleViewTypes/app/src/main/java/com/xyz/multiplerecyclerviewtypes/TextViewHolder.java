package com.xyz.multiplerecyclerviewtypes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TextViewHolder extends RecyclerView.ViewHolder {
    private ItemClickListener itemClickListener;
    private TextView mTvTitle;

    public TextViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        mTvTitle = itemView.findViewById(R.id.tvTitle);
    }

    public void setData(Model model) {
        mTvTitle.setText(model.getContent());
    }
}
