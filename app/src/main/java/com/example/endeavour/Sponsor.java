package com.example.endeavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Sponsor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Sponsor_Model> arrayList;
    private FirebaseRecyclerOptions<Sponsor_Model> options;
    private FirebaseRecyclerAdapter<Sponsor_Model,Sponsor_Viewholder> adapter;
    private DatabaseReference databaseReference;

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);

        recyclerView = findViewById(R.id.recyclerview_sponsor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("sponsors");
        databaseReference.keepSynced(true);

        arrayList = new ArrayList<Sponsor_Model>();

        options = new FirebaseRecyclerOptions.Builder<Sponsor_Model>().setQuery(databaseReference,Sponsor_Model.class).build();

        adapter = new FirebaseRecyclerAdapter<Sponsor_Model, Sponsor_Viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Sponsor_Viewholder sponsor_viewholder, int i, @NonNull Sponsor_Model sponsor_model) {
                sponsor_viewholder.sponsort.setText(sponsor_model.getSponsorname());
                sponsor_viewholder.sponsorC.setText(sponsor_model.getSponsorcategory());
                Picasso.get().load(sponsor_model.getImagesponsor()).into(sponsor_viewholder.sponsorImage);
            }

            @NonNull
            @Override
            public Sponsor_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Sponsor_Viewholder(LayoutInflater.from(Sponsor.this).inflate(R.layout.sponsor_example_item,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
