package com.example.endeavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeamMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Teamcard_model> arrayList;
    private FirebaseRecyclerOptions<Teamcard_model> options;
    private FirebaseRecyclerAdapter<Teamcard_model,Teamcard_Viewholder> adapter;
    private DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;
    boolean expanded = false;

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
        setContentView(R.layout.activity_team_main);

        if (firebaseDatabase == null) {
            firebaseDatabase=FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_view_teammain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("team");
        databaseReference.keepSynced(true);


        arrayList = new ArrayList<Teamcard_model>();

        options = new FirebaseRecyclerOptions.Builder<Teamcard_model>().setQuery(databaseReference,Teamcard_model.class).build();

        adapter = new FirebaseRecyclerAdapter<Teamcard_model, Teamcard_Viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Teamcard_Viewholder teamcard_viewholder, int i, @NonNull final Teamcard_model teamcard_model) {
                teamcard_viewholder.Name.setText(teamcard_model.getName());
                teamcard_viewholder.Desig.setText(teamcard_model.getDesig());
                teamcard_viewholder.Domain.setText(teamcard_model.getDomain());
                Picasso.get().load(teamcard_model.getImguri()).into(teamcard_viewholder.Imguri);

                teamcard_viewholder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (teamcard_viewholder.expand == false){
                            teamcard_viewholder.expandableLayout.setVisibility(View.VISIBLE);
                            teamcard_viewholder.expand = true;
                        }
                        else {
                            teamcard_viewholder.expandableLayout.setVisibility(View.GONE);
                            teamcard_viewholder.expand = false;
                        }
                    }
                });

                teamcard_viewholder.google_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(teamcard_model.getGoogle_profile());
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });

                teamcard_viewholder.linkedin_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(teamcard_model.getLinkedin_profile());
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });

                teamcard_viewholder.facebook_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(teamcard_model.getFacebook_profile());
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public Teamcard_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Teamcard_Viewholder(LayoutInflater.from(TeamMain.this).inflate(R.layout.example_item,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
