package com.xyz.addreplaceremove;

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
    private Button mBtnRemoveA;
    private Button mBtnReplaceAWithB;
    private Button mBtnAddB;
    private Button mBtnRemoveB;
    private Button mBtnReplaceBWithA;
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
        mBtnRemoveA = findViewById(R.id.btnRemoveA);
        mBtnReplaceAWithB = findViewById(R.id.btnReplaceAWithB);
        mBtnAddB = findViewById(R.id.btnAddB);
        mBtnRemoveB = findViewById(R.id.btnRemoveB);
        mBtnReplaceBWithA = findViewById(R.id.btnReplaceBWithA);
        mBtnAddA.setOnClickListener(this);
        mBtnAddB.setOnClickListener(this);
        mBtnRemoveA.setOnClickListener(this);
        mBtnReplaceAWithB.setOnClickListener(this);
        mBtnAddB.setOnClickListener(this);
        mBtnRemoveB.setOnClickListener(this);
        mBtnReplaceBWithA.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnAddA:
                addA();
                break;
            case R.id.btnRemoveA:
                removeA();
                break;
            case R.id.btnReplaceAWithB:
                replaceAWithB();
                break;
            case R.id.btnAddB:
                addB();
                break;
            case R.id.btnRemoveB:
                removeB();
                break;
            case R.id.btnReplaceBWithA:
                replaceBWithA();
                break;
        }
    }

    /**
     * Replace the existing fragment with FragmentA
     */
    private void replaceBWithA() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentTransaction.replace(R.id.flContainer, fragmentA, "FragmentA").commit();
    }

    private void removeB() {
        /*
        Check if FragmentB is present in the container using the TAG, if present then remove it.
         */
        FragmentB fragmentB = (FragmentB) fragmentManager.findFragmentByTag("FragmentB");
        if (fragmentB != null) {
            fragmentManager.beginTransaction().remove(fragmentB).commit();
        }
    }

    private void addB() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        fragmentTransaction.add(R.id.flContainer, fragmentB, "FragmentB").commit();
    }

    private void replaceAWithB() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        fragmentTransaction.replace(R.id.flContainer, fragmentB, "FragmentB").commit();
    }

    private void removeA() {
         /*
        Check if FragmentA is present in the container using the TAG, if present then remove it.
         */
        FragmentA fragmentA = (FragmentA) fragmentManager.findFragmentByTag("FragmentA");
        if (fragmentA != null) {
            fragmentManager.beginTransaction().remove(fragmentA).commit();
        }
    }

    private void addA() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentTransaction.add(R.id.flContainer, fragmentA, "FragmentA").commit();
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