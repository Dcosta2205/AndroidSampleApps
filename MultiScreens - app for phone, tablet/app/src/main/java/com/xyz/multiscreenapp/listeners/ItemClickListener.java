package com.xyz.multiscreenapp.listeners;

import com.xyz.multiscreenapp.model.VersionModel;

/**
 * @author Lloyd Dcosta
 * This listener is used to monitor the recycler view item clicks
 */
public interface ItemClickListener {

    void onRecyclerViewItemClicked(VersionModel versionModel, int position);
}
