package com.xyz.multiplerecyclerviewtypes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<Model> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        buildModelList();
        setRecyclerAdapter();
    }

    private void buildModelList() {
        modelList.add(new Model(Model.TEXT_TYPE, 0, "This is a simple Text Header"));
        modelList.add(new Model(Model.IMAGE_TYPE, R.drawable.sceneryone, "This is a simple Image View "));
        modelList.add(new Model(Model.AUDIO_TYPE, R.raw.shapeofyou, "Shape of you"));
        modelList.add(new Model(Model.TEXT_TYPE, 0, "This is a simple Text Header"));
        modelList.add(new Model(Model.IMAGE_TYPE, R.drawable.scenerytwo, "This is a simple Image View "));
        modelList.add(new Model(Model.AUDIO_TYPE, R.raw.shapeofyou, "Shape of you by Ed Sheeran"));
        modelList.add(new Model(Model.TEXT_TYPE, 0, "This is a simple Text Header"));
        modelList.add(new Model(Model.IMAGE_TYPE, R.drawable.sceneryone, "This is a simple Image View "));
        modelList.add(new Model(Model.AUDIO_TYPE, R.raw.shapeofyou, "Shape of You"));
        modelList.add(new Model(Model.TEXT_TYPE, 0, "This is a simple Text Header"));
        modelList.add(new Model(Model.IMAGE_TYPE, R.drawable.scenerytwo, "This is a simple Image View "));
        modelList.add(new Model(Model.AUDIO_TYPE, R.raw.shapeofyou, "Shape of you by Ed Sheeran"));
    }

    private void setRecyclerAdapter() {
        MultipleViewTypeAdapter multipleViewTypeAdapter = new MultipleViewTypeAdapter(modelList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(multipleViewTypeAdapter);
    }

    @Override
    public void onItemClicked(Model model, int position) {

    }
}