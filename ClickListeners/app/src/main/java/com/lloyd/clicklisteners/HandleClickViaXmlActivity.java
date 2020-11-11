package com.lloyd.clicklisteners;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity handles the view click via xml attribute android:onClick="handleClicks"
 */
public class HandleClickViaXmlActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_clicks); // Inflate the layout i.e so that the user can see it on the screen.
    }

    /**
     * This method is called when either of the view is clicked i.e called from xml and this method should be public
     * and should have only one method parameter view
     *
     * @param view the view that has been clicked
     */
    public void handleClicks(View view) {
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
