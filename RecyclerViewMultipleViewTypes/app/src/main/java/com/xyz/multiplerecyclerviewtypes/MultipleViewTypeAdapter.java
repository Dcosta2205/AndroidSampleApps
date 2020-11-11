package com.xyz.multiplerecyclerviewtypes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MultipleViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Model> modelList;
    private ItemClickListener itemClickListener;

    public MultipleViewTypeAdapter(List<Model> modelList, ItemClickListener itemClickListener) {
        this.modelList = modelList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case Model.AUDIO_TYPE:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_item_type, parent, false);
                return new AudioViewHolder(view, itemClickListener);
            case Model.IMAGE_TYPE:
                View imageViewType = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_type, parent, false);
                return new ImageViewHolder(imageViewType, itemClickListener);
            case Model.TEXT_TYPE:
                View textViewType = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_item_type, parent, false);
                return new TextViewHolder(textViewType, itemClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = modelList.get(position).getItemType();
        Model model = modelList.get(position);

        switch (viewType) {
            case Model.AUDIO_TYPE:
                if (holder instanceof AudioViewHolder) {
                    ((AudioViewHolder) holder).setData(model);
                }
                break;
            case Model.IMAGE_TYPE:
                if (holder instanceof ImageViewHolder) {
                    ((ImageViewHolder) holder).setData(model);
                }
                break;
            case Model.TEXT_TYPE:
                if (holder instanceof TextViewHolder) {
                    ((TextViewHolder) holder).setData(model);
                }

        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = modelList.get(position).getItemType();
        switch (itemType) {
            case Model.TEXT_TYPE:
                return Model.TEXT_TYPE;
            case Model.AUDIO_TYPE:
                return Model.AUDIO_TYPE;
            case Model.IMAGE_TYPE:
                return Model.IMAGE_TYPE;
        }
        return -1;
    }
}
