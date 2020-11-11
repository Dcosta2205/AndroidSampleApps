package com.xyz.servicehandlercommunication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Service used to make an api call and pass the data to the main activity via handler
 */
public class ApiService extends Service {

    private ApiServiceBinder binder = new ApiServiceBinder();
    private Handler mainThreadHandler;

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        Log.d("Lloyd", "onBind");
        callApi();
        return binder;
    }

    private void callApi() {
        Log.d("Lloyd", "onStartCommand");
        ApiClient apiClient = Network.getInstance().create(ApiClient.class);
        Call<ResponseModel> call = apiClient.getPosts();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                if (response.body() != null && mainThreadHandler != null) {
                    ResponseModel responseModel = response.body();
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = responseModel;
                    /*
                     * Build a message object by passing the necessary data and send message to the main thread handler
                     */
                    mainThreadHandler.sendMessage(message);

                    /*
                     * Once the data is being sent, stop the service
                     */
                    stopSelf();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                // Handle failure message here
            }
        });
    }

    public void setMainThreadHandler(Handler handler) {
        mainThreadHandler = handler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainThreadHandler = null;
    }

    public class ApiServiceBinder extends Binder {
        public ApiService getApiService() {
            return ApiService.this;
        }
    }

}
