package com.xyz.workmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button btnOneTimeRequest;
    private Button periodicWorkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        periodicWorkBtn = findViewById(R.id.btnPeriodicWork);
        btnOneTimeRequest = findViewById(R.id.btnOneTime);
        btnOneTimeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED).build();

                /*
                Inorder to pass objects we need to convert them to json and send
                 */
                Model model = new Model();
                model.setmData("Hey buddy");
                String s = new Gson().toJson(model);

                Data data = new Data.Builder().putString("key", s).build();

                /*
                Create a one time work request and pass the necessary constraints
                 */
                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setConstraints(constraints).
                        // Set the input data if any
                                setInputData(data)
                        .build();
                /*
                Start the work
                 */
                WorkManager.getInstance(MainActivity.this).enqueue(oneTimeWorkRequest);

                /*
                Observe the changes from the worker and display the result if any
                 */
                WorkManager.getInstance(MainActivity.this).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                        .observe(MainActivity.this, new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if (workInfo.getState() == WorkInfo.State.SUCCEEDED)
                                    Log.d("Lloyd", "Data from worker " + workInfo.getOutputData().getString("key"));
                            }
                        });
            }
        });

        periodicWorkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constraints constraintsPer = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED).build();
                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.
                        Builder(MyPeriodicWorker.class, 15, TimeUnit.MINUTES)
                        .setInitialDelay(10, TimeUnit.MINUTES)
                        .setConstraints(constraintsPer)
                        .build();
                WorkManager.getInstance().enqueue(periodicWorkRequest);
            }
        });
    }
}