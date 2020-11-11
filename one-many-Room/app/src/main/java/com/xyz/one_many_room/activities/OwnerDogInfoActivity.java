package com.xyz.one_many_room.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.xyz.one_many_room.R;
import com.xyz.one_many_room.adapter.OwnerDogInfoAdapter;
import com.xyz.one_many_room.database.DatabaseClient;
import com.xyz.one_many_room.interfaces.LooperPreparedListener;
import com.xyz.one_many_room.interfaces.RecyclerItemClickListener;
import com.xyz.one_many_room.model.BackgroundThread;
import com.xyz.one_many_room.model.DogAndOwner;
import com.xyz.one_many_room.model.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDogInfoActivity extends AppCompatActivity implements LooperPreparedListener,
        RecyclerItemClickListener {
    private RecyclerView recyclerView;
    private OwnerDogInfoAdapter adapter;
    private EditText mEtOwnerName;
    private LinearLayout mLlEdit;
    private Button mBtnEditAndSave;
    private BackgroundThread backgroundThread;
    private boolean isLooperPrepared;
    private DatabaseClient dataBaseClient;
    private List<DogAndOwner> dogAndOwnerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dog_info);
        initViews();
        setRecyclerAdapter();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        mEtOwnerName = findViewById(R.id.etOwnerName);
        mLlEdit = findViewById(R.id.llEditLayout);
        mBtnEditAndSave = findViewById(R.id.btnEdit);
        dataBaseClient = DatabaseClient.getDatabaseClient(this);
        backgroundThread = new BackgroundThread("db_thread", this);
        backgroundThread.start();

    }

    private void setRecyclerAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OwnerDogInfoAdapter(this, dogAndOwnerList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void looperPreparedListener() {
        isLooperPrepared = true;
        backgroundThread.addTaskToMessageQueue(new Runnable() {
            @Override
            public void run() {
                final List<DogAndOwner> dogsAndOwnersList = dataBaseClient.getAppDatabase().ownerDogDao().getDogsWithOwner();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(dogsAndOwnersList);
                    }
                });

            }
        });
    }

    @Override
    public void onEditDataClicked(int position, final DogAndOwner dogAndOwner) {
        mLlEdit.setVisibility(View.VISIBLE);
        mEtOwnerName.setText(dogAndOwner.getOwner().getOwnerName());
        mBtnEditAndSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlEdit.setVisibility(View.GONE);
                Owner updatedOwner = new Owner(dogAndOwner.getOwner().getOwnerId(), mEtOwnerName.getText().toString());
                backgroundThread.addTaskToMessageQueue(updateOwner(updatedOwner));
            }
        });
    }

    @Override
    public void onDeleteClicked(int position, final DogAndOwner dogAndOwner) {
        backgroundThread.addTaskToMessageQueue(new Runnable() {
            @Override
            public void run() {
                Owner owner = new Owner(dogAndOwner.getOwner().getOwnerId(),
                        dogAndOwner.getOwner().getOwnerName());
                dataBaseClient.getAppDatabase().ownerDogDao().
                        deleteOwner(owner);

                final List<DogAndOwner> dogAndOwnerList = dataBaseClient.getAppDatabase().ownerDogDao().getDogsWithOwner();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(dogAndOwnerList);
                    }
                });

            }
        });
    }


    private Runnable updateOwner(final Owner owner) {
        return new Runnable() {
            @Override
            public void run() {
                dataBaseClient.getAppDatabase().ownerDogDao().updateOwnerDetails(owner.getOwnerId(), owner.getOwnerName());
                final List<DogAndOwner> dogAndOwnerList = dataBaseClient.getAppDatabase().ownerDogDao().getDogsWithOwner();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(dogAndOwnerList);
                    }
                });
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backgroundThread.getLooper().quit();
    }
}