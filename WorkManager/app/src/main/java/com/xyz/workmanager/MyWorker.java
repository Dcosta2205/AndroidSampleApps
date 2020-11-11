package com.xyz.workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("Lloyd", Thread.currentThread().getName());

        if (getInputData().getString("key") != null) {
            Model model = new Gson().fromJson(getInputData().getString("key"),Model.class);
            Log.d("Lloyd", "Data from activity " + model.getmData());
        }
        for (int i = 0; i < 10; i++) {
            SystemClock.sleep(1000);
            Log.d("Lloyd", "" + i);

        }
        displayNotification("Hey I am your work", "Work Done");
        Data data = new Data.Builder().putString("key", "Done ").build();
        return Result.success(data);
    }

    private void displayNotification(String task, String desc) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("workExample", "workExample", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "workExample")
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(1, builder.build());
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.d("Lloyd", "Stopped");
    }
}
