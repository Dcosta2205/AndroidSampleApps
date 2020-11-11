package com.xyz.recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView;
    private List<Animal> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMenu);
        buildRecyclerData();
        setRecyclerAdapter();
    }

    private void setRecyclerAdapter() {
        AnimalAdapter menuAdapter = new AnimalAdapter(menuList, this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        /*
        Pass the data to the adapter class
         */
        recyclerView.setAdapter(menuAdapter);
    }

    private void buildRecyclerData() {
        menuList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {

        }
    }

    @Override
    public void onItemClicked(Animal menu, int position) {
        Toast.makeText(this, "Item clicked at position " + (position + 1), Toast.LENGTH_SHORT).show();
    }
}