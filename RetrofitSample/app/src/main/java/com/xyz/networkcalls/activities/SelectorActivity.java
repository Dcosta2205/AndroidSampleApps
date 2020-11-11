package com.xyz.networkcalls.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.networkcalls.R;

/**
 * This is the launcher activity and ask the user if he wants to make a Get or Post request.
 */
public class SelectorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnGetRequest;
    private Button mBtnPostRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        initViews();
    }

    private void initViews() {
        mBtnGetRequest = findViewById(R.id.btnGetRequests);
        mBtnPostRequest = findViewById(R.id.btnPostRequest);
        mBtnPostRequest.setOnClickListener(this);
        mBtnGetRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int resId = view.getId();
        switch (resId) {
            case R.id.btnGetRequests:
                Intent intent = new Intent(SelectorActivity.this, GetRequestsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPostRequest:
                Intent registerIntent = new Intent(SelectorActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }
}