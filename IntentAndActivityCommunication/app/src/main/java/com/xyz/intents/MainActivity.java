package com.xyz.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/*
This Activity demonstrates the use of Explicit intents
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnOpenSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViewsAndListeners();
    }

    private void initializeViewsAndListeners() {
        mBtnOpenSecondActivity = findViewById(R.id.btnOpenActivity);
        mBtnOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                This is an example for explicit intent , here you exactly know which activity has to be opened.
                 */
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                /*
                Key-value pairs that carry additional information required to accomplish the requested action.
                Just as some actions use particular kinds of data URIs, some actions also use particular extras.
                You can add extra data with various putExtra() methods, each accepting two parameters: the key name and the value.
                 */
                intent.putExtra("key", "Hey from Main Activity");
                startActivity(intent);
            }
        });
    }
}