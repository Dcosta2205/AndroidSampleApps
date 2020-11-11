package com.xyz.intents;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity receives the Data from main activity and sets the TextView text.
 */
public class SecondActivity extends AppCompatActivity {

    private TextView mTvIntentMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTvIntentMessage = findViewById(R.id.tvIntentMessage);
        getDataFromExtras();
    }

    private void getDataFromExtras() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            /*
            Here the textview text is assigned from the data that is received from MainActivity
             */
            mTvIntentMessage.setText(getIntent().getStringExtra("key"));
        }
    }
}