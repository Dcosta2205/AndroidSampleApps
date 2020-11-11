package com.xyz.fragmentcommunication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xyz.fragmentcommunication.R;

public class FragmentB extends Fragment {

    private TextView mTvDataFromA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvDataFromA = view.findViewById(R.id.tvData);

        getDataFromBundle();
    }

    private void getDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("data") != null) {
            mTvDataFromA.setText(bundle.getString("data"));
        }
    }
}