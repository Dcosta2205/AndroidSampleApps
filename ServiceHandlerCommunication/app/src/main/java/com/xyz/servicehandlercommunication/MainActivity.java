package com.xyz.servicehandlercommunication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lloyd Dcosta
 * This activity makes an API call in the service and once the response is fetched the data is
 * passed back to the main Activity via the Main Thread Handler.
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private TextView mTvBody;
    private Button mBtnStart;
    private Handler mainThreadHandler;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Lloyd", "onService connected");
            ApiService.ApiServiceBinder binder = (ApiService.ApiServiceBinder) service;
            ApiService apiService = binder.getApiService();
            /*
            Pass the main thread handler from main thread to the service to pass the API response
            from service to activity and update the UI
             */
            apiService.setMainThreadHandler(mainThreadHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Not required
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mTvBody = findViewById(R.id.tvBody);
        mTvTitle = findViewById(R.id.tvTitle);
        mBtnStart = findViewById(R.id.btnStartService);

        /*
         * Create a main thread handler and handle the incoming messages inside handleMessage
         */
        mainThreadHandler = new Handler(getMainLooper()) {

            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    /*
                    Get the response passed from the service and update the UI.
                     */
                    ResponseModel responseModel = (ResponseModel) msg.obj;
                    updateUI(responseModel);
                }
            }
        };

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApiService.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    /**
     * Once the response is passed from service to main activity via the handler update the UI
     *
     * @param responseModel api response that is passed from service to the main activity
     */
    private void updateUI(ResponseModel responseModel) {
        mTvTitle.setText(responseModel.getTitle());
        mTvBody.setText(responseModel.getBody());
    }

}