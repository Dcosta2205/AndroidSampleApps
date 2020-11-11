package com.xyz.networkcalls.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.networkcalls.R;
import com.xyz.networkcalls.adapter.PhotosListAdapter;
import com.xyz.networkcalls.model.PhotosResponse;
import com.xyz.networkcalls.network.ApiService;
import com.xyz.networkcalls.network.Network;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is used to fetch List  of photos from API and display the fetched list in recycler view
 */
public class PhotosListActivity extends AppCompatActivity {

    private PhotosListAdapter generalAdapter;
    private List<PhotosResponse> responseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_list);
        initViews();
        fetListOfPosts();
        setRecyclerAdapter();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        mProgressBar = findViewById(R.id.progressBar);
        /*
        Show the progress Bar till the list of posts is loaded from Api
         */
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void setRecyclerAdapter() {
        generalAdapter = new PhotosListAdapter(responseList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generalAdapter);
    }

    private void fetListOfPosts() {

              /*
        Initialize the API service calls which has all the Api calls
         */
        ApiService apiService = Network.getRetrofitInstance(this).create(ApiService.class);
        /*
        Make an API call and save the response in call object
         */
        Call<List<PhotosResponse>> call = apiService.getPhotos();

        /*
         * This enqueue method will make an asynchronous call and fetches the response from the server
         */
        call.enqueue(new Callback<List<PhotosResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<PhotosResponse>> call, @NonNull Response<List<PhotosResponse>> response) {
                /*
                This method is invoked if the response is successful
                 */
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    /*
                    Since to fetch the response it takes some time, we need to update the new fetched list and
                    notify the adapter about it
                     */
                    responseList = response.body();
                    generalAdapter.updateData(responseList);
                    mProgressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PhotosResponse>> call, @NonNull Throwable t) {
                /*
                If the Api call is failed then an error is thrown and it can be obtained from the throwable object
                 */
                Toast.makeText(PhotosListActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

}