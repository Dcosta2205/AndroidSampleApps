package com.xyz.dynamicfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * This activity adds 2 fragments dynamically on click of a button
 **/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirstFragment;
    private Button mBtnSecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnFirstFragment = findViewById(R.id.btnLaunchFirstFragment);
        mBtnSecondFragment = findViewById(R.id.btnSecondFragment);
        mBtnSecondFragment.setOnClickListener(this);
        mBtnFirstFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.btnLaunchFirstFragment:
                /*
                Create instance of First fragment
                 */
                FirstFragment firstFragment = new FirstFragment();
                /*
                  Add the first fragment to container flFirstFragment.
                 */
                fragmentTransaction.add(R.id.flFirstFragment, firstFragment, "First Fragment").commit();
                break;
            case R.id.btnSecondFragment:
                //Create instance of second fragment
                SecondFragment secondFragment = new SecondFragment();
                //Add the second fragment to flSecondFragment
                fragmentTransaction.add(R.id.flSecondFragment, secondFragment, "Second Fragment").commit();
                break;
        }
    }
}