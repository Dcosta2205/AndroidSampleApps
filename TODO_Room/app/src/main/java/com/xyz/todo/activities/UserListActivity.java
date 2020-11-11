package com.xyz.todo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xyz.todo.model.BackgroundThread;
import com.xyz.todo.database.DatabaseClient;
import com.xyz.todo.listeners.LooperPreparedListener;
import com.xyz.todo.R;
import com.xyz.todo.listeners.RecyclerItemClickListener;
import com.xyz.todo.model.User;
import com.xyz.todo.adapter.UserListAdapter;

import java.util.List;

/**
 * @author Lloyd Dcosta
 * This Activity displays the List of student stored in the database, where you can search for a
 * particular student, edit the details of the student, delete the student from database
 */
public class UserListActivity extends AppCompatActivity implements RecyclerItemClickListener, LooperPreparedListener {

    private List<User> usersList;
    private RecyclerView recyclerView;
    private SearchView mSearchView;
    private UserListAdapter adapter;
    private EditText mEtName;
    private EditText mEtLastName;
    private LinearLayout mLlEdit;
    private Button mBtnEditAndSave;
    private BackgroundThread backgroundThread;
    private boolean isLooperPrepared;
    private DatabaseClient databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        initViews();
        getDataFromIntent();
        setRecyclerAdapter();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        mSearchView = findViewById(R.id.searchView);
        mEtName = findViewById(R.id.etName);
        mEtLastName = findViewById(R.id.etLastName);
        mLlEdit = findViewById(R.id.llEditLayout);
        mBtnEditAndSave = findViewById(R.id.btnEdit);
        databaseClient = DatabaseClient.getInstance(this);
        backgroundThread = new BackgroundThread("db_thread", this);
        backgroundThread.start();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                if (isLooperPrepared){
                    backgroundThread.addTaskToQueue(searchUserByName(name));
                }
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String studentName) {

                return false;
            }
        });
    }

    private void setRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserListAdapter(usersList, this);
        recyclerView.setAdapter(adapter);
    }

    private void getDataFromIntent() {
        if (getIntent() != null && getIntent().getSerializableExtra("userList") != null) {
            usersList = (List<User>) getIntent().getSerializableExtra("userList");
        }
    }

    @Override
    public void onEditDataClicked(int position, final User user) {
        mLlEdit.setVisibility(View.VISIBLE);
        mEtName.setText(user.getName());
        mEtLastName.setText(user.getLastName());
        mBtnEditAndSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlEdit.setVisibility(View.GONE);
                User updatedUser = new User(mEtName.getText().toString(), mEtLastName.getText().toString());
                updatedUser.setId(user.getId());
                if (isLooperPrepared) {
                    backgroundThread.addTaskToQueue(updateToDB(updatedUser));
                }
            }
        });
    }

    @Override
    public void onDeleteClicked(int position, User user) {
        if (isLooperPrepared) {
            backgroundThread.addTaskToQueue(deleteUserRunnable(user));
        }
    }


    private Runnable updateToDB(final User user) {
        return new Runnable() {
            @Override
            public void run() {
                databaseClient.appDatabase().userDao().update(user);
                final List<User> newUserList = databaseClient.appDatabase().userDao().getAllUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(newUserList);
                    }
                });
            }
        };
    }


    private Runnable deleteUserRunnable(final User user) {
        return new Runnable() {
            @Override
            public void run() {
                databaseClient.appDatabase().userDao().delete(user);
                final List<User> newUserList = databaseClient.appDatabase().userDao().getAllUsers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(newUserList);
                    }
                });
            }
        };
    }

    private Runnable searchUserByName(final String name) {
        return new Runnable() {
            @Override
            public void run() {
                final List<User> userList = databaseClient.appDatabase().userDao().getUserByName(name);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(userList);
                    }
                });
            }
        };
    }

    @Override
    public void onLooperPrepared() {
        isLooperPrepared = true;
    }

}