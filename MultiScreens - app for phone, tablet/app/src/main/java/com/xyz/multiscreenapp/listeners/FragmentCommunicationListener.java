package com.xyz.multiscreenapp.listeners;

import com.xyz.multiscreenapp.model.VersionModel;

/**
 * @author Lloyd Dcosta
 * This interface is used to communicate between the fragments
 */
public interface FragmentCommunicationListener {

    void onDataPassed(VersionModel model);
}
