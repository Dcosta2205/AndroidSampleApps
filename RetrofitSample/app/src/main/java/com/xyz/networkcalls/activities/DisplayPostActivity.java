package com.xyz.networkcalls.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xyz.networkcalls.R;
import com.xyz.networkcalls.model.Post;
import com.xyz.networkcalls.network.ApiService;
import com.xyz.networkcalls.network.Network;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * This Activity will display a specify post . i.e the post id is passed by user via Edittext.
 */
public class DisplayPostActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private TextView mTvBody;
    private int postId;
    private FrameLayout mProgressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_post);
        initViews();
        getDataFromIntent();
        fetchPost();
    }

    private void fetchPost() {
       /*
        Initialize the API service calls which has all the Api calls
         */
        ApiService apiService = Network.getRetrofitInstance(this).create(ApiService.class);
        /*
        Make an API call and save the response in call object
         */
        Call<Post> call = apiService.getPost(postId);

        /*
         * This enqueue method will make an asynchronous call and fetches the response from the server
         */
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull retrofit2.Response<Post> response) {
                /*
                This method is invoked if the response is successful
                 */
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    /*
                   Now since the response received is successful, we need to set the data to textviews
                     */
                    if (response.body() != null) {
                        Post post = response.body();
                        mTvTitle.setText(post.getTitle());
                        mTvBody.setText(post.getBody());
                    }

                    mProgressBarLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                /*
                If the Api call is failed then an error is thrown and it can be obtained from the throwable object
                 */
                Toast.makeText(DisplayPostActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBarLayout.setVisibility(View.GONE);
            }
        });
    }

    /*
    Which post user wants to see ? The post id is passed by the user from GetRequestActivity with the
    help of a EditText
     */
    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getExtras() != null) {
            postId = getIntent().getExtras().getInt("postId");
        }
    }

    private void initViews() {
        mTvBody = findViewById(R.id.tvBody);
        mTvTitle = findViewById(R.id.tvTitle);
        mProgressBarLayout = findViewById(R.id.flProgressBar);
        /*
        show the progress bar
         */
        mProgressBarLayout.setVisibility(View.VISIBLE);
    }
}