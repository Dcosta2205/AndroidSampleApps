package com.xyz.popfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtnAddA;
    private Button mBtnAddB;
    private Button mBtnAddC;
    private Button mBtnPopFragment;
    private Button mBtnPopInclusive;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        printLogs("onCreate");
    }

    private void initViews() {
        mBtnAddA = findViewById(R.id.btnAddA);
        mBtnAddB = findViewById(R.id.btnAddB);
        mBtnAddC = findViewById(R.id.btnAddC);
        mBtnPopFragment = findViewById(R.id.btnPop);
        mBtnPopInclusive = findViewById(R.id.btnPopFragmentInclusive);
        mBtnAddA.setOnClickListener(this);
        mBtnAddB.setOnClickListener(this);
        mBtnAddC.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        mBtnPopInclusive.setOnClickListener(this);
        mBtnPopFragment.setOnClickListener(this);
        /*
         * This Listener is called every time whenever backstack changes . i.e when a fragment is added to back stack/ fragment is popped from stack.
         */
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d(TAG, "back stack changed ");
                int backCount = fragmentManager.getBackStackEntryCount();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnAddA:
                addA();
                break;
            case R.id.btnAddB:
                addB();
                break;
            case R.id.btnAddC:
                addC();
                break;
            case R.id.btnPop:
                popFragment();
                break;
            case R.id.btnPopFragmentInclusive:
                popFragmentInclusive();
                break;

        }
    }

    /**
     * This method pops all the fragments including the specified fragment (here B, if A, B and C are added to back stack then it will remove fragments till B)
     */
    private void popFragmentInclusive() {
        fragmentManager.popBackStack("B", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Pops (removes) the fragment that is at the top of the stack.
     */
    private void popFragment() {
        fragmentManager.popBackStack();
    }

    private void addC() {
        /*
        Check if FragmentB is present in the container using the TAG, if present then remove it.
         */
        FragmentC fragmentC = new FragmentC();
        fragmentManager.beginTransaction().add(R.id.flContainer, fragmentC, "FragmentC").commit();

    }

    private void addB() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        fragmentTransaction.add(R.id.flContainer, fragmentB, "FragmentB").addToBackStack("B").commit();
    }

    private void addA() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentTransaction.add(R.id.flContainer, fragmentA, "FragmentA").addToBackStack("A").commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        printLogs("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        printLogs("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        printLogs("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        printLogs("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        printLogs("onDestroy");
    }


    private void printLogs(String message) {
        Log.d(TAG, message);
    }
}