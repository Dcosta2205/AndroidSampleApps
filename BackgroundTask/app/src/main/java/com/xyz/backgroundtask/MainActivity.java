package com.xyz.backgroundtask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mBtnClick;
    private TextView mTvUpdate;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //This method is called as soon as the system encounters thread.start(); and its called in a separate thread.
            Log.d("LLoyd", "Thread name 2 " + Thread.currentThread().getName());
            for (int i = 0; i < 1000000000; i++) {
                for (int j = 0; j < 2; j++) {

                }
            }
            Log.d("Lloyd", "For loop executed");

            /*
            Uncomment the below line and observe the crash in the LogCat
             */
//            mTvUpdate.setText("Updated");

            /*
            The below method will be invoked in main thread i.e Its not possible to update the UI from background thread
             so when we call runOnUiThread() , the run method inside runOnUiThread() will be invoked in main thread and we can update the UI.
             */
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("Lloyd", "Thread name " + Thread.currentThread().getName());
                    mTvUpdate.setText("Updated");
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {

        mBtnClick = findViewById(R.id.btnClick);
        mTvUpdate = findViewById(R.id.tvUpdate);
        Log.d("LLoyd", "Thread name 1" + Thread.currentThread().getName());
        startBackgroundThread();


        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button clicked ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * This method is used to start a background Thread.
     */
    private void startBackgroundThread() {
        Thread thread = new Thread(runnable);
        //The started is started only if the start method is called
        thread.start();
    }
}