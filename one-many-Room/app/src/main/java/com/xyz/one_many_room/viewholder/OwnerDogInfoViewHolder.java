package com.xyz.one_many_room.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.one_many_room.R;
import com.xyz.one_many_room.interfaces.RecyclerItemClickListener;
import com.xyz.one_many_room.model.DogAndOwner;

public class OwnerDogInfoViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemClickListener listener;
    private TextView mTvOwnerName;
    private TextView mTvDogName;
    private ImageView mIvEdit;
    private ImageView mIvDelete;
    private TextView mTvDogBreed;

    public OwnerDogInfoViewHolder(@NonNull View itemView, RecyclerItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvOwnerName = itemView.findViewById(R.id.tvOwnerName);
        mTvDogName = itemView.findViewById(R.id.tvDogName);
        mIvEdit = itemView.findViewById(R.id.ivEdit);
        mIvDelete = itemView.findViewById(R.id.ivDelete);
        mTvDogBreed = itemView.findViewById(R.id.tvDogBreed);
    }

    public void setData(final DogAndOwner dogAndOwner) {
        mTvDogName.setText(" ");
        mTvDogBreed.setText(" ");
        for (int i = 0; i < dogAndOwner.getDog().size(); i++) {
            mTvDogName.setText(mTvDogName.getText() + " " + dogAndOwner.getDog().get(i).getDogName());
            mTvDogBreed.setText(mTvDogBreed.getText() + " " + dogAndOwner.getDog().get(i).getDogBreed());
        }
        mTvOwnerName.setText(dogAndOwner.getOwner().getOwnerName());

        mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditDataClicked(getAdapterPosition(), dogAndOwner);
            }
        });

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(getAdapterPosition(), dogAndOwner);
            }
        });
    }
}
