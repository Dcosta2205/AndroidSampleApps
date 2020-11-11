package com.xyz.broadcastreceivers.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Lloyd Dcosta
 * This broad cast receiver is triggered only for Api (<26 below oreo) when the user switches Airplane mode.
 */
public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Airplane mode From AirplaneModeReceiver", Toast.LENGTH_SHORT).show();
    }
}
