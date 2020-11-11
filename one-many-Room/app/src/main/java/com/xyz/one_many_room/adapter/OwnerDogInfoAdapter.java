package com.xyz.one_many_room.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.xyz.one_many_room.R;
import com.xyz.one_many_room.interfaces.RecyclerItemClickListener;
import com.xyz.one_many_room.model.DogAndOwner;
import com.xyz.one_many_room.viewholder.OwnerDogInfoViewHolder;

import java.util.List;

public class OwnerDogInfoAdapter extends RecyclerView.Adapter<OwnerDogInfoViewHolder> {

    private RecyclerItemClickListener listener;
    private List<DogAndOwner> dogAndOwnerList;

    public OwnerDogInfoAdapter(RecyclerItemClickListener listener, List<DogAndOwner> dogAndOwnerList) {
        this.listener = listener;
        this.dogAndOwnerList = dogAndOwnerList;
    }

    @NonNull
    @Override
    public OwnerDogInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_layout,
                parent, false);
        return new OwnerDogInfoViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerDogInfoViewHolder holder, int position) {
        DogAndOwner dogAndOwner = dogAndOwnerList.get(position);
        holder.setData(dogAndOwner);
    }

    @Override
    public int getItemCount() {
        return dogAndOwnerList.size();
    }

    public void updateData(List<DogAndOwner> dogAndOwners) {
        this.dogAndOwnerList = dogAndOwners;
        notifyDataSetChanged();
    }
}
