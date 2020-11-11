package com.xyz.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This Activity demonstrates the use of explicit intent
 */
public class ExplicitIntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);
        initializeViewsAndListeners();
    }

    private void initializeViewsAndListeners() {
        Button mBtnOpenLink = findViewById(R.id.btnOpenLink);
        Button mBtnShare = findViewById(R.id.btnShare);
        mBtnShare.setOnClickListener(this);
        mBtnOpenLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        /*
        Here we are not specifying what has to be opened exactly where as the system will
        find the applications that are suitable to open this kind of intent.
         */
        if (id == R.id.btnShare) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Data");
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"foo@bar.com"});
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Testing subject");
            sendIntent.putExtra(Intent.EXTRA_CC, new String[]{"lloyd@masaiSchool.com"});
            sendIntent.setType("message/rfc822");

            // Verify that the intent will resolve to an activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
        } else if (id == R.id.btnOpenLink) {
            /*
            This intent will give user options to open the specified link using the applications that
            support this kind of intent action
             */
            Intent viewIntent = new Intent();
            viewIntent.setAction(Intent.ACTION_VIEW);
            viewIntent.setData(Uri.parse("https://www.yahoo.com"));

            // Verify that the intent will resolve to an activity
            if (viewIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(viewIntent);
            }
        }
    }
}