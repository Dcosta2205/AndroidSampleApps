package com.xyz.basicservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author This service runs indefinetly in background and downloads a file and writes it to internal storage
 */
public class DownloadFileService extends Service implements HandlerReadyListener {
    private BackgroundTask backgroundTask;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Lloyd", "onCreate");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            showNotificationAndStartForeGround();
        } else {
            startForeground(1, new Notification());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        backgroundTask = new BackgroundTask("Service Thread", this);
        backgroundTask.start();
        return START_REDELIVER_INTENT;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void showNotificationAndStartForeGround() {
        String NOTIFICATION_CHANNEL_ID = "lloyd";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setContentText("Hey music is playing")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    /**
     * This method is used to download a file in Background.
     *
     * @return runnable
     */
    private Runnable createTask() {
        return new
                Runnable() {
                    @Override
                    public void run() {
                        Log.d("Lloyd", Thread.currentThread().getName());
                        /*
                        getFilesDir will create a file in the internal storage
                         */
                        File directory = new File(getFilesDir() + File.separator + "My Folder1");
                        /*
                        Check if the directory exists else create one
                         */
                        if (!directory.exists()) {
                            directory.mkdir();
                        }

                        /*
                        Create new file in the created directory and give a name to the file
                         */
                        File newFile = new File(directory, "lloyd.html");

                        if (!newFile.exists()) {
                            try {
                                newFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        InputStream stream = null;
                        FileOutputStream fos = null;
                        try {

                            URL url = new URL("https://www.vogella.com/index.html");
                            stream = url.openConnection().getInputStream();
                            InputStreamReader reader = new InputStreamReader(stream);
                            fos = new FileOutputStream(newFile);
                            int next = -1;
                            while ((next = reader.read()) != -1) {
                                fos.write(next);
                            }
                            // successfully finished and send a broadcast to the activity and show a toast

                            Intent intent = new Intent("com.xyz.download");
                            sendBroadcast(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (stream != null) {
                                try {
                                    stream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            /*
                             * Once the file is downloaded stop the service
                             */
//                            stopSelf();
                        }
                    }
                };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Lloyd", "onDestroy");

    }

    /**
     * This method is called as soon as the handlers looper is ready. Once the looper is ready
     * add the task to the message queue and run in the background
     */
    @Override
    public void onHandlerPrepared() {
        backgroundTask.addTaskToMessageQueue(createTask());
    }
}
