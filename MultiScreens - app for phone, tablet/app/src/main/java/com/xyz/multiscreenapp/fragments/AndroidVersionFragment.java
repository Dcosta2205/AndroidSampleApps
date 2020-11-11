package com.xyz.multiscreenapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.multiscreenapp.R;
import com.xyz.multiscreenapp.adapter.VersionAdapter;
import com.xyz.multiscreenapp.listeners.FragmentCommunicationListener;
import com.xyz.multiscreenapp.listeners.ItemClickListener;
import com.xyz.multiscreenapp.model.VersionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lloyd Dcosta
 * this fragment is used to display the android version names in a recycler view
 */
public class AndroidVersionFragment extends Fragment implements ItemClickListener {

    private RecyclerView mRecyclerView;
    private List<VersionModel> versionModelList = new ArrayList<>();
    private FragmentCommunicationListener fragmentCommunicationListener;

    public AndroidVersionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_android_version, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        buildRecyclerData();
        setRecyclerViewAdapter();
    }

    private void setRecyclerViewAdapter() {
        VersionAdapter versionAdapter = new VersionAdapter(versionModelList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(versionAdapter);
    }

    private void buildRecyclerData() {
        versionModelList.add(new VersionModel("Cup cake", "It was released in the April 27, 2009"));
        versionModelList.add(new VersionModel("Donut", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Eclair", "Released in the year October 26, 2009"));
        versionModelList.add(new VersionModel("Froyo", "Released in the year May 20, 2010"));
        versionModelList.add(new VersionModel("Gingerbread", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Honeycomb", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Ice Cream Sandwich", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Jelly Bean", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Lollipop", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Marshmallow", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Lollipop", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Marshmallow", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Lollipop", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Nougat", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Lollipop", "Released in the year Sept 15, 2009"));
        versionModelList.add(new VersionModel("Oreo", "Released in the year Sept 15, 2009"));
    }

    /**
     * This listener is invoked when the recycler view item is clicked
     *
     * @param versionModel that data of the item that has been clicked by the user
     * @param position     position of the item clicked
     */
    @Override
    public void onRecyclerViewItemClicked(VersionModel versionModel, int position) {
        /*
        Once the item is clicked notify the activity that the item has been clicked and open
        VersionDetailsFragment
         */
        if (fragmentCommunicationListener != null) {
            fragmentCommunicationListener.onDataPassed(versionModel);
        }
    }

    /**
     * This method is used to initialize the FragmentCommunicationListener
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentCommunicationListener = (FragmentCommunicationListener) context;
    }
}