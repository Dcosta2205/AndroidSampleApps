package com.xyz.receivebroadcast.activities;

import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.receivebroadcast.R;
import com.xyz.receivebroadcast.receivers.BroadCastReceiverWithPermission;
import com.xyz.receivebroadcast.receivers.BroadCastReceiverWithoutPermission;

public class MainActivity extends AppCompatActivity {
    private BroadCastReceiverWithoutPermission broadCastReceiverWithoutPermission;
    private BroadCastReceiverWithPermission broadCastReceiverWithPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerBroadCastWithoutPermission();
        registerBroadCastWithPermission();
    }

    /**
     * This method is used to register the BroadcastReceiver which is used to receive broadcast with permissions
     */
    private void registerBroadCastWithPermission() {
        IntentFilter intentFilter = new IntentFilter("com.lloyd.broadcast.withPermission");
        broadCastReceiverWithPermission = new BroadCastReceiverWithPermission();
        registerReceiver(broadCastReceiverWithPermission, intentFilter, Manifest.permission.ACCESS_NETWORK_STATE, null);
    }

    /**
     * This method is used to register the BroadcastReceiver which is used to receive broadcast without permissions
     */
    private void registerBroadCastWithoutPermission() {
        IntentFilter intentFilter = new IntentFilter("com.lloyd.broadcast");
        broadCastReceiverWithoutPermission = new BroadCastReceiverWithoutPermission();
        registerReceiver(broadCastReceiverWithoutPermission, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCastReceiverWithoutPermission);
        unregisterReceiver(broadCastReceiverWithPermission);
    }
}