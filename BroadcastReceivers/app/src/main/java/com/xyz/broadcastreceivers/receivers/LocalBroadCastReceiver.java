package com.xyz.broadcastreceivers.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xyz.broadcastreceivers.activities.LocalBroadCastReceiverActivity;

public class LocalBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            Log.d("Lloyd", intent.getAction());
            Intent activityIntent = new Intent(context, LocalBroadCastReceiverActivity.class);
            activityIntent.putExtra("data", intent.getStringExtra("data"));
            context.startActivity(activityIntent);
        }
    }
}
