package com.lloyd.clicklisteners;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity handles click events for two views defined in the XML
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    /*
       Declare an object of type Button and connect it with the one in the Xml
     */
    private Button mBtnClickMe;
    /*
    Declare an object of type TextView and connect with the one in xml.
     */
    private TextView mTvHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Inflate the layout i.e so that the user can see it on the screen.
        initViewsAndListeners();
    }

    /**
     * This method initializes all the views and listeners
     */
    private void initViewsAndListeners() {
        mBtnClickMe = findViewById(R.id.btnClickMe); // connects the button defined in the xml using the id
        mTvHelloWorld = findViewById(R.id.tvHelloWorld); // connects the textView defined in the xml using the id.

        /*
        This method needs to be called if the user wants to get the click events on the "click me" button.
         */
        mBtnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button clicked"); // called when the user clicks the button
            }
        });

        /*
        This method needs to be called if the user wants to get the click events on the "Hello World" TextView.
         */
        mTvHelloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Text View clicked"); // called when the user clicks the text view
            }
        });

    }
}