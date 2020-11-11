package com.xyz.sqlitedatabase.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.sqlitedatabase.R;
import com.xyz.sqlitedatabase.interfaces.RecyclerItemClickListener;
import com.xyz.sqlitedatabase.model.Student;

public class StudentListViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemClickListener listener;
    private TextView mTvName;
    private TextView mTvAddress;
    private ImageView mIvEdit;
    private ImageView mIvDelete;

    public StudentListViewHolder(@NonNull View itemView, RecyclerItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvName = itemView.findViewById(R.id.tvName);
        mTvAddress = itemView.findViewById(R.id.tvAddress);
        mIvEdit = itemView.findViewById(R.id.ivEdit);
        mIvDelete = itemView.findViewById(R.id.ivDelete);
    }

    public void setData(final Student student) {
        mTvAddress.setText(student.getAddress());
        mTvName.setText(student.getName());

        mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditDataClicked(getAdapterPosition(), student);
            }
        });

        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(getAdapterPosition(), student);
            }
        });
    }
}
