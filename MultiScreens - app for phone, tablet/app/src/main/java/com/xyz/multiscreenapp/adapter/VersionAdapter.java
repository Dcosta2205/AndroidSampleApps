package com.xyz.multiscreenapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multiscreenapp.listeners.ItemClickListener;
import com.xyz.multiscreenapp.R;
import com.xyz.multiscreenapp.model.VersionModel;
import com.xyz.multiscreenapp.viewholder.VersionViewHolder;

import java.util.List;

/**
 * @author Lloyd Dcosta
 * This is an Adapter class which has the version model data
 */
public class VersionAdapter extends RecyclerView.Adapter<VersionViewHolder> {
    private List<VersionModel> versionModelList;
    private ItemClickListener itemClickListener;

    public VersionAdapter(List<VersionModel> modelList, ItemClickListener itemClickListener) {
        versionModelList = modelList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new VersionViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        VersionModel versionModel = versionModelList.get(position);
        holder.setData(versionModel);
    }

    @Override
    public int getItemCount() {
        return versionModelList.size();
    }
}
