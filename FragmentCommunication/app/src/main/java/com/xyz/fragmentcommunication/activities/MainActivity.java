package com.xyz.fragmentcommunication.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.fragmentcommunication.R;
import com.xyz.fragmentcommunication.fragments.FragmentA;
import com.xyz.fragmentcommunication.fragments.FragmentB;
import com.xyz.fragmentcommunication.interfaces.FragmentListener;

/**
 * This Activity launches a FragmentA as the user opens the app, and from FragmentA user
 * sends a message to FragmentB
 */
public class MainActivity extends AppCompatActivity implements FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchFragmentA();
    }

    private void launchFragmentA() {
        FragmentA fragmentA = new FragmentA();
        /*
        This is the method used to send FragmentListener from Activity to FragmentA
         */
        fragmentA.setFragmentListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.flContainer, fragmentA, "FragmentA").commit();
    }

    @Override
    public void onFragmentDataPassed(Bundle bundle) {
        Toast.makeText(this, "Data has to be passed", Toast.LENGTH_SHORT).show();
        /*
          Once the Pass Data button is clicked in Fragment A , we get callback here and here we need to launch fragmentB
         */
        launchFragmentB(bundle);
    }

    /*
    This method is used to launch FragmentB along with the data received from FragmentA. (addToBackStack() is optional here).
     */
    private void launchFragmentB(Bundle bundle) {
        FragmentB fragmentB = new FragmentB();
        /*
        Set the data to fragmentB received from FragmentA via the listener
         */
        fragmentB.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragmentB, "FragmentB").addToBackStack("FragmentB").commit();
    }
}