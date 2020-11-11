package com.xyz.expandcollapserecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FamilyViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvFamilyName;
    private LinearLayout mLlDynamic;
    private RecyclerItemClickListener listener;
    private boolean isClicked = false;

    public FamilyViewHolder(@NonNull View itemView, RecyclerItemClickListener listener) {
        super(itemView);
        initViews(itemView);
        this.listener = listener;
    }

    private void initViews(View view) {
        mTvFamilyName = view.findViewById(R.id.tvFamilyName);
        mLlDynamic = view.findViewById(R.id.llDynamic);
    }

    public void setData(Model model) {
        mTvFamilyName.setText(model.getFamilyName());
        if (model.isExpanded()) {
            mLlDynamic.removeAllViews();
            mLlDynamic.setVisibility(View.VISIBLE);
            mTvFamilyName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);
            for (int i = 0; i < model.getFamilyMemberNames().size(); i++) {
                View view = LayoutInflater.from(mTvFamilyName.getContext())
                        .inflate(R.layout.student_details, null, false);
                TextView textView = view.findViewById(R.id.tvStudentName);
                textView.setText(model.getFamilyMemberNames().get(i));
                mLlDynamic.addView(view);
            }

        } else {
            mLlDynamic.setVisibility(View.GONE);
        }


        mTvFamilyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isClicked) {
//                    if (mLlDynamic.getChildCount() > 0) {
//                        mTvFamilyName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);
//                        mLlDynamic.setVisibility(View.VISIBLE);
//                    } else {
//                        mLlDynamic.setVisibility(View.VISIBLE);
//                        mTvFamilyName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_less, 0);
//                        for (int i = 0; i < model.getFamilyMemberNames().size(); i++) {
//                            View view = LayoutInflater.from(mTvFamilyName.getContext())
//                                    .inflate(R.layout.student_details, null, false);
//                            TextView textView = view.findViewById(R.id.tvStudentName);
//                            textView.setText(model.getFamilyMemberNames().get(i));
//                            mLlDynamic.addView(view);
//                        }
//                    }
//                    isClicked = true;
//                } else {
//                    mLlDynamic.setVisibility(View.GONE);
//                    isClicked = false;
//                    mTvFamilyName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_expand_more, 0);
//                }
                listener.onExpandClicked(model, getAdapterPosition());
            }
        });


    }
}
