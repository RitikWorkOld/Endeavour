package com.example.endeavour.Events_Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.endeavour.Events_main_model;
import com.example.endeavour.Events_main_viewholder;
import com.example.endeavour.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Events_Corp extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Events_main_model> arrayList;
    private FirebaseRecyclerOptions<Events_main_model> options;
    private FirebaseRecyclerAdapter<Events_main_model, Events_main_viewholder> adapter;
    private DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events__corp,container,false);

        recyclerView = view.findViewById(R.id.rv_events_corp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("eventscorp");
        databaseReference.keepSynced(true);

        arrayList = new ArrayList<Events_main_model>();

        options = new FirebaseRecyclerOptions.Builder<Events_main_model>().setQuery(databaseReference,Events_main_model.class).build();

        adapter = new FirebaseRecyclerAdapter<Events_main_model, Events_main_viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Events_main_viewholder viewholder, int i, @NonNull final Events_main_model model) {
                viewholder.Title.setText(model.getTitle());
                viewholder.Descp.setText(model.getDescp());
                Picasso.get().load(model.getMimguri()).into(viewholder.Mimguri);

                String Title = model.getTitle();
                String Descp = model.getDescp();
                String Desc1 = model.getDesc1();
                String Desc2 = model.getDesc2();
                String Mimguri = model.getMimguri();
                String Simguri = model.getSimguri();
                String Register_uri = model.getRegister_uri();

                final Bundle bundle = new Bundle();
                bundle.putString("Title",Title);
                bundle.putString("Descp",Descp);
                bundle.putString("Desc1",Desc1);
                bundle.putString("Desc2",Desc2);
                bundle.putString("Mimguri",Mimguri);
                bundle.putString("Simguri",Simguri);
                bundle.putString("Register_uri",Register_uri);

                viewholder.read_more_ebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        events_details events_details = new events_details();
                        events_details.setArguments(bundle);

                        fragmentTransaction.replace(R.id.events_container,events_details);
                        fragmentTransaction.commit();
                    }
                });
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
