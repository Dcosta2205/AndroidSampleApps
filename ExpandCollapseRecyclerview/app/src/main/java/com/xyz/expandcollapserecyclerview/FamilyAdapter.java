package com.xyz.expandcollapserecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyViewHolder> {
    private List<Model> modelList;
    private RecyclerItemClickListener listener;

    public FamilyAdapter(List<Model> modelList, RecyclerItemClickListener listener) {
        this.modelList = modelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FamilyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new FamilyViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyViewHolder holder, int position) {
        Model model = modelList.get(position);
        holder.setData(model);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
