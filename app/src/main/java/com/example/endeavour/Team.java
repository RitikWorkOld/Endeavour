package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Team extends AppCompatActivity {
    public RecyclerView mRecyclerView;

    List<ExampleItem> exampleList;

    private String TAG="TEAM Class";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_team);

        Log.d(TAG, "running: ");

        mRecyclerView = findViewById(R.id.recycler_view_team);
        mRecyclerView.setHasFixedSize(true);

        initData();
        initRecyclerView();
    }

    private void initData() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.user, "Line 1", "Line 2","Hello1"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 3", "Line 4","Hello2"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 5", "Line 6","Hello3"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 7", "Line 8","Hello4"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 9", "Line 10","Hello5"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 11", "Line 12","Hello6"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 13", "Line 14","Hello7"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 15", "Line 16","Hello8"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 17", "Line 18","Hello9"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 19", "Line 20","Hello10"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 21", "Line 22","Hello11"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 23", "Line 24","Hello12"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 25", "Line 26","Hello13"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 27", "Line 28","Hello14"));
        exampleList.add(new ExampleItem(R.drawable.user, "Line 29", "Line 30","Hello15"));
    }

    private void initRecyclerView() {

        ExampleAdapter exampleAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(exampleAdapter);

    }
}
