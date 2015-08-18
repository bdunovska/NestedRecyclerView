package com.example.belladunovska.nestedrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter verticalAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView myRecyclerView;
    private ArrayList<String> data;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        data = new ArrayList<>();
        //this list is a dummy data I use to hold the number of rows.
        for (int i = 0; i < 100; i++) {
            data.add(String.valueOf(i));
        }
        mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(mLayoutManager);
        verticalAdapter = new MyVerticalAdapter(this, data);
        myRecyclerView.setAdapter(verticalAdapter);
    }


}
