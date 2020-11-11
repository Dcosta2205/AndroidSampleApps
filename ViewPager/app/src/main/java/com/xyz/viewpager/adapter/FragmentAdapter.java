package com.xyz.viewpager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.xyz.viewpager.fragments.FragmentA;
import com.xyz.viewpager.fragments.FragmentB;
import com.xyz.viewpager.fragments.FragmentC;

/**
 * This class gives the fragments as the user swipes the screen horizontally
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    /**
     * This method is called when the user swipes the screen horizontally
     *
     * @param position position of the screen user wants to see
     * @return returns a fragment based on the position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentA.newInstance("This is First Fragment");
            case 1:
                return FragmentB.newInstance("This is second Fragment");
            case 2:
                return FragmentC.newInstance("This is Third Fragment");
        }
        return FragmentA.newInstance("This is Default Fragment");
    }

    /**
     * Total number of fragments to be seen in the view pager class
     */
    @Override
    public int getCount() {
        return 3;
    }
}
