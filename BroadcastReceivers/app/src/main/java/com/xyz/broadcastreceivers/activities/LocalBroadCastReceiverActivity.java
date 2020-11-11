package com.xyz.broadcastreceivers.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.broadcastreceivers.R;

/**
 * @author Lloyd Dcosta
 * This Activity is called when a local broadcast is sent from MainActivity
 */
public class LocalBroadCastReceiverActivity extends AppCompatActivity {

    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broad_cast_receiver);
        mTvData = findViewById(R.id.tvData);
        if (getIntent() != null && getIntent().getExtras() != null) {
            mTvData.setText(getIntent().getStringExtra("data"));
        }
    }
}