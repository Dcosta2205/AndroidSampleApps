package com.xyz.receivebroadcast.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.receivebroadcast.R;

/**
 * @author Lloyd Dcosta
 * This activity is opened when a broadcast is sent from the broadcast app with some restricted permissions.
 */
public class BroadCastWithPermissionReceiverActivity extends AppCompatActivity {

    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_on_broad_cast);
        initViews();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            mTvData.setText(getIntent().getStringExtra("key"));
        }
    }

    private void initViews() {
        mTvData = findViewById(R.id.tvData);
    }
}