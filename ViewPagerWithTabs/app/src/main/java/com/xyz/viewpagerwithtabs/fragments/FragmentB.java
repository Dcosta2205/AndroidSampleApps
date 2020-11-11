package com.xyz.viewpagerwithtabs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xyz.viewpagerwithtabs.R;
import com.xyz.viewpagerwithtabs.utils.Utils;


public class FragmentB extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = FragmentB.class.getSimpleName();
    private TextView mTvData;
    private String mParam1;

    public FragmentB() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentB.
     */
    public static FragmentB newInstance(String param1) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.printLogs(TAG, "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Utils.printLogs(TAG, "onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Utils.printLogs(TAG, "onViewCreated");
        mTvData = view.findViewById(R.id.tvData);
        mTvData.setText(mParam1);
    }


    @Override
    public void onStart() {
        super.onStart();
        Utils.printLogs(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.printLogs(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils.printLogs(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Utils.printLogs(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Utils.printLogs(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.printLogs(TAG, "onDestroy");
    }
}