package com.xyz.viewpagercircleindicator.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.xyz.viewpagercircleindicator.fragments.FragmentA;
import com.xyz.viewpagercircleindicator.fragments.FragmentB;
import com.xyz.viewpagercircleindicator.fragments.FragmentC;


/**
 * This class gives the fragments as the user swipes the screen horizontally
 */
public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * This method is called when the user swipes the screen horizontally
     *
     * @param position position of the screen user wants to see
     * @return returns a fragment based on the position
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return FragmentA.newInstance("This is First Fragment, So Welcome to circle indicators. Hope you are doing Amazing");
            case 1:
                return FragmentB.newInstance("This is second Fragment");
            case 2:
                return FragmentC.newInstance("This is Third Fragment");
        }
        return FragmentA.newInstance("This is Default Fragment");
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
