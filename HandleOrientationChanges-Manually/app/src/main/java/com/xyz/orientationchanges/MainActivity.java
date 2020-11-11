package com.xyz.orientationchanges;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView mTvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvData = findViewById(R.id.tvData);
        Log.d("Lloyd", "onCreate");
    }

    /**
     * This method is only called iff we specify android:configChanges="orientation|screenLayout|screenSize|keyboardHidden"
     * in the manifest file
     *
     * @param newConfig this object will give the information about the changed configuration
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mTvData.setText("Landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mTvData.setText("Portrait");
        }
    }
}