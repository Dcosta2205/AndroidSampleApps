package com.xyz.recyclerview;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnimalViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    private ItemClickListener itemClickListener;

    public AnimalViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        initViews(itemView);
        this.itemClickListener = itemClickListener;
    }

    private void initViews(View view) {
        imageView = view.findViewById(R.id.ivAnimal);

    }

    public void setData( Animal animal) {
//        imageView.setImageResource(animal.getResId());
    }
}
