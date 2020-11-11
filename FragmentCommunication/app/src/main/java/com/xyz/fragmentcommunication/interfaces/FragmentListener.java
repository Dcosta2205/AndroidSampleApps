package com.xyz.fragmentcommunication.interfaces;

import android.os.Bundle;

/**
 * This listener is used to communicate between fragments.
 */
public interface FragmentListener {

    void onFragmentDataPassed(Bundle bundle);
}
