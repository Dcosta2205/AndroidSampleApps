package com.xyz.viewpager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xyz.viewpager.R;
import com.xyz.viewpager.adapter.FragmentAdapter;

/**
 * This Activity demonstrates a simple view pager where there are 3 Fragments
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewPager);
        setViewPagerAdapter();
    }

    /**
     * This method sets data to the View Pager Adapter class
     */
    private void setViewPagerAdapter() {
        /*
        Please note that FragmentAdapter is not a inbuilt class, we need to create it.
         */
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(fragmentAdapter);
    }
}