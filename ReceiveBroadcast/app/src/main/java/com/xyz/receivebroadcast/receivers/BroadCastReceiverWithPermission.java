package com.xyz.receivebroadcast.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xyz.receivebroadcast.activities.BroadCastWithPermissionReceiverActivity;

public class BroadCastReceiverWithPermission extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null && intent.getAction() != null) {
            String name = intent.getAction();
            Log.d("Lloyd", name);
            /*
            Get the data sent from BroadcastReceivers app and open the new activity
             */
            Intent activityIntent = new Intent(context, BroadCastWithPermissionReceiverActivity.class);
            activityIntent.putExtra("key", intent.getStringExtra("data"));
            context.startActivity(activityIntent);
        }
    }
}
