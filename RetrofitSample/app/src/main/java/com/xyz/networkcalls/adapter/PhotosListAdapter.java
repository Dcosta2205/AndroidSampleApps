package com.xyz.networkcalls.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.networkcalls.viewholder.PhotosListViewHolder;
import com.xyz.networkcalls.R;
import com.xyz.networkcalls.model.PhotosResponse;

import java.util.List;

public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListViewHolder> {

    private List<PhotosResponse> responseList;

    public PhotosListAdapter(List<PhotosResponse> responseList) {
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PhotosListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new PhotosListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosListViewHolder holder, int position) {
        PhotosResponse response = responseList.get(position);
        holder.setData(response);
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public void updateData(List<PhotosResponse> responseList){
        this.responseList = responseList;
        notifyDataSetChanged();
    }
}
