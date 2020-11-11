package com.xyz.todo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.todo.model.BackgroundThread;
import com.xyz.todo.database.DatabaseClient;
import com.xyz.todo.listeners.LooperPreparedListener;
import com.xyz.todo.R;
import com.xyz.todo.model.User;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LooperPreparedListener {

    private EditText mEtName;
    private EditText mEtLastName;
    private Button mBtnSave;
    private Button mBtnFetch;
    private boolean isLooperPrepared;
    private BackgroundThread thread;
    private DatabaseClient databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mEtLastName = findViewById(R.id.etLastName);
        mEtName = findViewById(R.id.etName);
        mBtnSave = findViewById(R.id.btnSave);
        mBtnFetch = findViewById(R.id.btnFetch);
        mBtnSave.setOnClickListener(this);
        mBtnFetch.setOnClickListener(this);
        databaseClient = DatabaseClient.getInstance(this);
        thread = new BackgroundThread("db_thread", this);
        thread.start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSave:
                if (isDataValid() && isLooperPrepared) {
                    thread.addTaskToQueue(insertToDB());
                }
                break;
            case R.id.btnFetch:
                if (isLooperPrepared) {
                    thread.addTaskToQueue(fetchFromDB());
                }
                break;
        }
    }

    private boolean isDataValid() {
        if (mEtName.getText().toString().isEmpty()) {
            mEtName.setError("Name Field is empty");
            return false;
        }
        if (mEtLastName.getText().toString().isEmpty()) {
            mEtLastName.setError("last name Field is empty");
            return false;
        }
        return true;
    }

    @Override
    public void onLooperPrepared() {
        isLooperPrepared = true;
    }

    private Runnable insertToDB() {
        return new Runnable() {
            @Override
            public void run() {
                User user = new User(mEtName.getText().toString(), mEtLastName.getText().toString());
                databaseClient.appDatabase().userDao().insertUser(user);
            }
        };
    }

    private Runnable fetchFromDB() {
        return new Runnable() {
            @Override
            public void run() {
                List<User> userList = databaseClient.appDatabase().userDao().getAllUsers();
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                intent.putExtra("userList", (Serializable) userList);
                startActivity(intent);
            }
        };
    }
}