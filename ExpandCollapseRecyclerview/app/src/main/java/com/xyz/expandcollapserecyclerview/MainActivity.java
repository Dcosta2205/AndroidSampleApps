package com.xyz.expandcollapserecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener {

    private RecyclerView recyclerView;
    private List<Model> modelList;
    private FamilyAdapter adapter;
    private int new_pos = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buildModelList();
        setRecyclerAdapter();
    }

    private void setRecyclerAdapter() {
        adapter = new FamilyAdapter(modelList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void buildModelList() {
        modelList = new ArrayList<>();
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Arbeena");
        memberNames.add("Abhinav");
        memberNames.add("Sai");
        memberNames.add("Vaibhav");
        memberNames.add("Nagalakshmi");
        memberNames.add("Shivraj");
        memberNames.add("Gourav");
        memberNames.add("Mukesh");
        modelList.add(new Model("Trojans", memberNames, false, -1));

        List<String> members = new ArrayList<>();
        members.add("Lloyd");
        members.add("Albert");
        members.add("Nrupul");
        members.add("Ayush");
        modelList.add(new Model("Masai", members, false, -1));
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);

    }

    @Override
    public void onExpandClicked(Model model, int position) {

        /*
        Collapsing
         */
        if (new_pos != -1) {
            Model m = modelList.get(new_pos);
            m.setExpanded(false);
            adapter.notifyItemChanged(new_pos);
        }

        // If same layout is clicked
        if (new_pos == position) {
            new_pos = -1;
            return;
        } else

            new_pos = position;

        if (!model.isExpanded()) {
            modelList.set(position, new Model(model.getFamilyName(), model.getFamilyMemberNames(), true, position));
        }

        adapter.notifyItemChanged(position);

    }
}