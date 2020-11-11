package com.xyz.servicehandlercommunication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("/posts/1")
    Call<ResponseModel> getPosts();
}
