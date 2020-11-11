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
import com.xyz.networkcalls.adapter.PostListAdapter;
import com.xyz.networkcalls.model.PostQuery;
import com.xyz.networkcalls.network.ApiService;
import com.xyz.networkcalls.network.Network;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * This activity displays the List of posts which the user wants to see. User will pass the post Id
 * from GetRequestsActivity and the list that belongs to that specif post Id is displayed in a RecyclerView.
 */
public class PostListActivity extends AppCompatActivity {

    private PostListAdapter postListAdapter;
    private List<PostQuery> responseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar mProgressBar;
    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        initViews();
        getDataFromIntent();
        fetchListOfPosts();
        setRecyclerAdapter();
    }

    private void fetchListOfPosts() {
              /*
        Initialize the API service calls which has all the Api calls
         */
        ApiService apiService = Network.getRetrofitInstance(this).create(ApiService.class);
        /*
        Make an API call and save the response in call object
         */
        Call<List<PostQuery>> call = apiService.getListOfPost(postId);

        /*
         * This enqueue method will make an asynchronous call and fetches the response from the server
         */
        call.enqueue(new Callback<List<PostQuery>>() {
            @Override
            public void onResponse(@NonNull Call<List<PostQuery>> call, @NonNull retrofit2.Response<List<PostQuery>> response) {
                /*
                This method is invoked if the response is successful
                 */
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    /*
                    Since to fetch the response it takes some time, we need to update the new fetched list and
                    notify the adapter about it
                     */
                    responseList = response.body();
                    postListAdapter.updateData(responseList);
                    mProgressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PostQuery>> call, @NonNull Throwable t) {
                /*
                If the Api call is failed then an error is thrown and it can be obtained from the throwable object
                 */
                Toast.makeText(PostListActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }
        });

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
        postListAdapter = new PostListAdapter(responseList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postListAdapter);
    }

    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            postId = getIntent().getExtras().getInt("postId");
        }
    }
}