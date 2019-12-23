package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class Team extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String TAG="TEAM Class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_team);

        Log.d(TAG, "running: ");

       // team m bhi aaya.. yaani problem yha h


        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.user, "Line 1", "Line 2"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 3", "Line 4"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 5", "Line 6"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 7", "Line 8"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 9", "Line 10"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 11", "Line 12"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 13", "Line 14"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 15", "Line 16"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 17", "Line 18"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 19", "Line 20"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 21", "Line 22"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 23", "Line 24"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 25", "Line 26"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 27", "Line 28"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 29", "Line 30"));

        mRecyclerView = findViewById(R.id.recycler_view_team);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ExampleAdapter(exampleList);


        mRecyclerView.setAdapter(mAdapter);
    }
}
