package com.xyz.dynamicviews;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * @author Lloyd Dcosta
 * This activity is used to create dynamic text views and dynamic view which is already present in the xml
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnAddViews;
    private LinearLayout mLlDynamicView;
    private Button mBtnDynamicViews;
    private LinearLayout mLlDynamicLayout;
    private int i = 0;
    private int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mBtnAddViews = findViewById(R.id.btnAddViews);
        mLlDynamicView = findViewById(R.id.llAddViews);
        mBtnDynamicViews = findViewById(R.id.btnDynamicLayout);

        /*
        On click of button we are creating text views and adding it to the layout inside the xml
         */
        mBtnAddViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(MainActivity.this);
                textView.setText("Value is " + ++i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                textView.setPadding(30, 12, 12, 0);
                textView.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(params);
                mLlDynamicView.addView(textView);
            }
        });


        mBtnDynamicViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Here we already have an layout which we are inflating it dynamically and adding it to the activity_main.xml
                 */
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dynamic_layout, null, false);

                TextView tvName = view.findViewById(R.id.tvName);
                TextView tvAge = view.findViewById(R.id.tvAge);

                tvName.setText("Prateek " + ++j);
                tvAge.setText("Age " + ++j);

                mLlDynamicView.addView(view);
            }
        });
    }
}