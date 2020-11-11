package com.xyz.viewpagerwithtabs.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xyz.viewpagerwithtabs.R;
import com.xyz.viewpagerwithtabs.adapter.FragmentAdapter;


/**
 * This Activity demonstrates a simple view pager where there are 3 Fragments
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
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
    }

    /**
     * This method sets data to the View Pager Adapter class
     */
    private void setViewPagerAdapter() {
        /*
        Please note that FragmentAdapter is not a inbuilt class, we need to create it.
         */
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(fragmentAdapter);
        /*
        Link the tab layout with the view pager
         */
        tabLayout.setupWithViewPager(mViewPager);
    }
}