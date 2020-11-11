package com.xyz.multiscreenapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xyz.multiscreenapp.R;

/**
 * @author Lloyd Dcosta
 * This fragment displays the data of the Android version that the user has clicked
 */
public class VersionDetailsFragment extends Fragment {

    private TextView mTvVersionName;
    private TextView mTvDescription;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_version_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        getDataFromBundles();
    }

    private void getDataFromBundles() {
        if (getArguments() != null) {
            String versionName = getArguments().getString("versionName");
            String description = getArguments().getString("description");
            mTvVersionName.setText(versionName);
            mTvDescription.setText(description);
        }
    }

    private void initViews(View view) {
        mTvDescription = view.findViewById(R.id.tvVersionDescription);
        mTvVersionName = view.findViewById(R.id.tvVersionName);
    }
}