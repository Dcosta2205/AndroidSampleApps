package com.xyz.sqlitedatabase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.xyz.sqlitedatabase.R;
import com.xyz.sqlitedatabase.database.DBManager;
import com.xyz.sqlitedatabase.model.Student;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lloyd Dcosta
 * This Activity is used to enter the student details and save it to the database.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DBManager dbManager;
    private EditText mEtName;
    private EditText mEtAddress;
    private Button mBtnSave;
    private Button mBtnFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
        dbManager = DBManager.getInstance(this);
    }

    private void initViewsAndListeners() {
        mEtAddress = findViewById(R.id.etAddress);
        mEtName = findViewById(R.id.etName);
        mBtnSave = findViewById(R.id.btnSave);
        mBtnFetch = findViewById(R.id.btnFetch);
        mBtnSave.setOnClickListener(this);
        mBtnFetch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSave:
                if (isDataValid()) {
                    Thread thread = new Thread(insertTaskToDB());
                    thread.start();
                }
                break;
            case R.id.btnFetch:
                Thread thread = new Thread(fetchFromDB());
                thread.start();
                break;
        }
    }

    private boolean isDataValid() {
        if (mEtName.getText().toString().isEmpty()) {
            mEtName.setError("Name Field is empty");
            return false;
        }
        if (mEtAddress.getText().toString().isEmpty()) {
            mEtAddress.setError("Address Field is empty");
            return false;
        }
        return true;
    }

    /**
     * Insert the data to the database in the background thread
     *
     * @return return a runnable to start a thread
     */
    private Runnable insertTaskToDB() {
        return new Runnable() {
            @Override
            public void run() {
                dbManager.insert(mEtName.getText().toString(), mEtAddress.getText().toString());
            }
        };
    }

    /**
     * Fetches the data from the database and pass the List of students fetched from Database
     * to the StudentListActivity
     *
     * @return return a runnable to start a thread
     */
    private Runnable fetchFromDB() {
        return new Runnable() {
            @Override
            public void run() {
                final List<Student> studentList = dbManager.fetch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        Start a new Activity and pass the data and populate it in a recycler view
                         */
                        Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                        intent.putExtra("studentList", (Serializable) studentList);
                        startActivity(intent);
                    }
                });
            }
        };
    }

    /**
     * Close the Database once the app is killed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}