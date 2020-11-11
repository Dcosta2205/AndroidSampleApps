package com.xyz.networkcalls.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.networkcalls.R;

/**
 * This class demonstrates the different type of Get request and the result of all the requests is
 * handled in separate activities
 */
public class GetRequestsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPostId;
    private EditText etQueryId;
    private Button mBtnPost;
    private Button mBtnQuery;
    private Button mBtnListPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etPostId = findViewById(R.id.etPostNumer);
        etQueryId = findViewById(R.id.etQueryId);
        mBtnPost = findViewById(R.id.btnPostNumber);
        mBtnQuery = findViewById(R.id.btnPostQuery);
        mBtnListPost = findViewById(R.id.btnGetListOfPosts);
        mBtnPost.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnListPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnPostNumber:
                Intent intent = new Intent(this, DisplayPostActivity.class);
                intent.putExtra("postId", Integer.parseInt(etPostId.getText().toString().trim()));
                startActivity(intent);
                break;
            case R.id.btnPostQuery:
                Intent postQueryActivity = new Intent(this, PostListActivity.class);
                postQueryActivity.putExtra("postId", Integer.parseInt(etQueryId.getText().toString().trim()));
                startActivity(postQueryActivity);
                break;
            case R.id.btnGetListOfPosts:
                Intent photosIntent = new Intent(this, PhotosListActivity.class);
                startActivity(photosIntent);
                break;
        }
    }
}