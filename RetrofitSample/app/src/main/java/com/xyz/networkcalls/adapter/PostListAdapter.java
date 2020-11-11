package com.xyz.networkcalls.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.networkcalls.R;
import com.xyz.networkcalls.model.PhotosResponse;
import com.xyz.networkcalls.model.PostQuery;
import com.xyz.networkcalls.viewholder.PostListViewHolder;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListViewHolder> {

    private List<PostQuery> postQueryList;

    public PostListAdapter(List<PostQuery> postQueryList) {
        this.postQueryList = postQueryList;
    }

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_items, parent, false);
        return new PostListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        PostQuery postQuery = postQueryList.get(position);
        holder.setData(postQuery);
    }

    @Override
    public int getItemCount() {
        return postQueryList.size();
    }

    public void updateData(List<PostQuery> responseList){
        this.postQueryList = responseList;
        notifyDataSetChanged();
    }
}
