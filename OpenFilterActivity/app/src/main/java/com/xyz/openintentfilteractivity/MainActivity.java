package com.xyz.openintentfilteractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity has a button on click of which, it will be open "IntentFilter" app as it as the same intent action.
 * Make sure IntentFilter app is installed first
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        Button mBtnOpenIntentFilterApp = findViewById(R.id.btnOpenIntentFilterApp);
        mBtnOpenIntentFilterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.xyz.intent.action");

                /*
                When the below code is executed, it will check for all the activities who has the above mentioned action,
                if it matches then it will open the activity. If there are multiple activities that matches the same action
                then it lets user choose between those activities
                 */
                startActivity(intent);
            }
        });
    }
}