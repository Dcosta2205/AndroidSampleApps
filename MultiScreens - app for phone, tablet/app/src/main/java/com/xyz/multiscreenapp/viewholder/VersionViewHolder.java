package com.xyz.multiscreenapp.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multiscreenapp.R;
import com.xyz.multiscreenapp.model.VersionModel;
import com.xyz.multiscreenapp.listeners.ItemClickListener;

public class VersionViewHolder extends RecyclerView.ViewHolder {
    private ItemClickListener itemClickListener;
    private TextView mTvVersionName;


    public VersionViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        mTvVersionName = itemView.findViewById(R.id.tvItemName);
    }

    public void setData(final VersionModel versionModel) {
        mTvVersionName.setText(versionModel.getVersionName());
        mTvVersionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onRecyclerViewItemClicked(versionModel,getAdapterPosition());
            }
        });
    }
}
