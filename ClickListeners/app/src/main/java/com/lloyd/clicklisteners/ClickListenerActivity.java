package com.lloyd.clicklisteners;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity handles click of two views by implementing View.OnClickListener interface
 */
public class ClickListenerActivity extends AppCompatActivity implements View.OnClickListener {

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
        mBtnClickMe.setOnClickListener(this); // We need to set the click listeners on the view so that the callback is received in onClick(View view) method
        mTvHelloWorld.setOnClickListener(this);
    }

    /**
     * This method is called when either of the view is clicked
     *
     * @param view the view that has been clicked
     */
    @Override
    public void onClick(View view) {
        /*
        Use switch case if there are more than 3 views whose clicks have to be handled.
         */
        if (view.getId() == R.id.btnClickMe) {
            Log.d(TAG, "Button clicked"); // called when the user clicks the button
        } else if (view.getId() == R.id.tvHelloWorld) {
            Log.d(TAG, "Text View clicked"); // called when the user clicks the text view
        }
    }
}
