package com.xyz.basicservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Lloyd Dcosta
 * The broadcast receiver is invoked once the file has been downloaded.
 */
public class DownloadBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hey, File Downloaded successfully ", Toast.LENGTH_SHORT).show();
    }
}
