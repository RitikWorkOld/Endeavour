package com.example.endeavour.Events_Fragments;

import android.content.Context;
import android.icu.text.CaseMap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.endeavour.EventsMain;
import com.example.endeavour.Events_main_model;
import com.example.endeavour.Events_main_viewholder;
import com.example.endeavour.R;
import com.example.endeavour.Teamcard_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.endeavour.R.layout.fragment_events__fun;

public class Events_Fun extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Events_main_model> arrayList;
    private FirebaseRecyclerOptions<Events_main_model> options;
    private FirebaseRecyclerAdapter<Events_main_model, Events_main_viewholder> adapter;
    private DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events__fun,container,false);

        if (firebaseDatabase == null) {
            firebaseDatabase=FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }

        recyclerView = view.findViewById(R.id.rv_events_fun);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("eventsfun");
        databaseReference.keepSynced(true);

        arrayList = new ArrayList<Events_main_model>();

        options = new FirebaseRecyclerOptions.Builder<Events_main_model>().setQuery(databaseReference,Events_main_model.class).build();

        adapter = new FirebaseRecyclerAdapter<Events_main_model, Events_main_viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Events_main_viewholder viewholder, int i, @NonNull final Events_main_model model) {
                viewholder.Title.setText(model.getTitle());
                viewholder.Descp.setText(model.getDescp());
                Picasso.get().load(model.getMimguri()).into(viewholder.Mimguri);
            }

            @NonNull
            @Override
            public Events_main_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Events_main_viewholder(LayoutInflater.from(view.getContext()).inflate(R.layout.layout_event_card,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        return view;
    }
}
