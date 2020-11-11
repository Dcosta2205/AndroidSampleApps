package com.xyz.basicservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lloyd Dcosta
 * This Activity is used to start a service
 */
public class MainActivity extends AppCompatActivity {

    private DownloadBroadCastReceiver broadCastReceiver;
    private Button mBtnStartService;
    private Button mBtnStopService;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        broadCastReceiver = new DownloadBroadCastReceiver();
        registerReceiver(broadCastReceiver, new IntentFilter("com.xyz.download"
        ));

    }

    private void initViewsAndListeners() {
        mBtnStartService = findViewById(R.id.btnStartService);
        mBtnStopService = findViewById(R.id.btnStopService);
        intent = new Intent(MainActivity.this, DownloadFileService.class);
        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        mBtnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCastReceiver);
    }
}