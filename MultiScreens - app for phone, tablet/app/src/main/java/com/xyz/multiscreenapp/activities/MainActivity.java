package com.xyz.multiscreenapp.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.multiscreenapp.R;
import com.xyz.multiscreenapp.fragments.AndroidVersionFragment;
import com.xyz.multiscreenapp.fragments.VersionDetailsFragment;
import com.xyz.multiscreenapp.listeners.FragmentCommunicationListener;
import com.xyz.multiscreenapp.model.VersionModel;

/**
 * @author Lloyd Dcosta
 * This activity is used to display 2 fragments one at a time for handsets and 2 fragments at a time for tablet devices.
 */
public class MainActivity extends AppCompatActivity implements FragmentCommunicationListener {

    private boolean isDualContainerPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = findViewById(R.id.versionContainer);

        /*
        frameLayout is not null for tablet layouts.
         */
        if (frameLayout != null) {
            isDualContainerPresent = true;
        }

        /*
           Launch the Android version Fragment for both tablet and handset. The tablet will have
           2 containers which will hold 2 fragments where as handset will have only one fragment at a time.
         */
        if (isDualContainerPresent) {
            launchAndroidVersionFragment(R.id.versionContainer);
        } else {
            launchAndroidVersionFragment(R.id.container);
        }
    }

    /**
     * This will launch the Android version fragment.
     */
    private void launchAndroidVersionFragment(int containerId) {
        AndroidVersionFragment androidVersionFragment = new AndroidVersionFragment();
        getSupportFragmentManager().beginTransaction().replace(containerId, androidVersionFragment, "AndroidVersionFragment").commit();
    }

    /**
     * This interface method is called when the recycler item is clicked in the AndroidVersionFragment
     * and the data is sent to Activity via this method and the VersionDetailsFragment is launched.
     *
     * @param model Model class that has the data
     */
    @Override
    public void onDataPassed(VersionModel model) {
        VersionDetailsFragment versionDetailsFragment = new VersionDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("versionName", model.getVersionName());
        bundle.putString("description", model.getVersionDescription());
        versionDetailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, versionDetailsFragment, "VersionDetail").
                addToBackStack("VersionDetail").commit();
    }
}