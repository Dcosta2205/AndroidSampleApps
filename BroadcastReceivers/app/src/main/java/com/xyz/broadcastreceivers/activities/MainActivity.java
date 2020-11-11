package com.xyz.broadcastreceivers.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.broadcastreceivers.R;
import com.xyz.broadcastreceivers.receivers.LocalBroadCastReceiver;

/**
 * @author Lloyd Dcosta
 * This Activity sends various broadcast to other apps as well as within the app.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSendBroadCast;
    private Button mBtnSendBroadCastWithPermissions;
    private Button mBtnLocalBroadCast;
    private LocalBroadCastReceiver localBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        registerLocalBroadCast();
    }

    private void registerLocalBroadCast() {
        localBroadCastReceiver = new LocalBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter("com.lloyd.localBroadCast");
        registerReceiver(localBroadCastReceiver, intentFilter);
    }

    private void initViewsAndListeners() {
        mBtnSendBroadCast = findViewById(R.id.btnSendBroadCastToApp);
        mBtnSendBroadCastWithPermissions = findViewById(R.id.btnSendBroadCastWithPermissions);
        mBtnLocalBroadCast = findViewById(R.id.btnLocalBroadcast);
        mBtnSendBroadCast.setOnClickListener(this);
        mBtnSendBroadCastWithPermissions.setOnClickListener(this);
        mBtnLocalBroadCast.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnSendBroadCastToApp:
                Intent intent = new Intent();
                intent.setAction("com.lloyd.broadcast");
                intent.putExtra("data", "Hey, From Send BroadCast Activity");
                /*
                sendBroadcast will send the broadcast across the app i.e if any of the app that is
                listening to this broadcast who action matches ("com.lloyd.broadcast") then the callback
                is received in that app if the app is in background (not killed).
                 */
                sendBroadcast(intent);
                break;
            case R.id.btnSendBroadCastWithPermissions:
                Intent i = new Intent();
                i.setAction("com.lloyd.broadcast.withPermission");
                i.putExtra("data", "Hey, From Send BroadCast Activity but restricted with permissions");
                /*
                sendBroadcast will send the broadcast across the app i.e if any of the app that is
                listening to this broadcast who action matches ("com.lloyd.broadcast") then the callback
                is received in that app if the app is in background (not killed).
                 */
                sendBroadcast(i, Manifest.permission.ACCESS_NETWORK_STATE);

                break;
            case R.id.btnLocalBroadcast:
                /*
                 * this Broadcast will be received within this app
                 */
                Intent localIntent = new Intent();
                localIntent.setAction("com.lloyd.localBroadCast");
                localIntent.putExtra("data", "This is Local Broadcast");
                sendBroadcast(localIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(localBroadCastReceiver);
    }
}