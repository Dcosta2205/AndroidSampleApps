package com.xyz.viewpagercircleindicator.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xyz.viewpagercircleindicator.R;
import com.xyz.viewpagercircleindicator.adapter.FragmentAdapter;


/**
 * This Activity demonstrates a simple view pager where there are 3 Fragments
 */
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager2 mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViewPagerAdapter();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(this);
    }

    /**
     * This method sets data to the View Pager Adapter class
     */
    private void setViewPagerAdapter() {
        /*
        Please note that FragmentAdapter is not a inbuilt class, we need to create it.
         */
        FragmentAdapter fragmentAdapter = new FragmentAdapter(this);
        mViewPager.setAdapter(fragmentAdapter);

        /*
        A mediator to link a TabLayout with a ViewPager2. The mediator will synchronize the ViewPager2's position
         with the selected tab when a tab is selected, and the TabLayout's scroll position when the user drags the ViewPager2.
         */
        new TabLayoutMediator(tabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                Log.d("Lloyd", "onConfigureTab called");
            }
        }).attach();
    }

    /*
    called when the user drags or scrolls horizontally on the view pager
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.d("Lloyd", "onTabSelected");
    }

    /*
    Called when the user goes to the next tab i.e unselected the previous tab
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        Log.d("Lloyd", "onTabUnselected");
    }

    /*
    If the user clicks on the current tab
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Log.d("Lloyd", "onTabReselected");
    }
}