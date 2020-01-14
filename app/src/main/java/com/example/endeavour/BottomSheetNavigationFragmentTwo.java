package com.example.endeavour;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.endeavour.Events_Fragments.Events_main_model;
import com.example.endeavour.Events_Fragments.Events_main_viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class BottomSheetNavigationFragmentTwo extends BottomSheetDialogFragment {


    public static BottomSheetNavigationFragmentTwo newInstance() {

        Bundle args = new Bundle();

        BottomSheetNavigationFragmentTwo fragmentTwo = new BottomSheetNavigationFragmentTwo();
        fragmentTwo.setArguments(args);
        return fragmentTwo;
    }

    //Bottom Sheet Callback
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            //check the slide offset and change the visibility of close button
            if (slideOffset > 0.5) {
                closeButton1.setVisibility(View.VISIBLE);
            } else {
                closeButton1.setVisibility(View.GONE);
            }
        }
    };

    private ImageView closeButton1;
    private RecyclerView recyclerView;
    private ArrayList<Glimpses> arrayList;
    private FirebaseRecyclerOptions<Glimpses> options;
    private FirebaseRecyclerAdapter<Glimpses,Glimpses_Viewholder> adapter;
    private DatabaseReference databaseReference;

    //TextView one;
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Get the content View
        final View contentView = View.inflate(getContext(), R.layout.bottom_navigation_drawer_two, null);
        dialog.setContentView(contentView);

        closeButton1 = contentView.findViewById(R.id.close_image_view);

        recyclerView = contentView.findViewById(R.id.rv_glimp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(contentView.getContext(),2));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("glimpses");
        databaseReference.keepSynced(true);

        arrayList = new ArrayList<Glimpses>();

        options = new FirebaseRecyclerOptions.Builder<Glimpses>().setQuery(databaseReference,Glimpses.class).build();

        adapter = new FirebaseRecyclerAdapter<Glimpses, Glimpses_Viewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Glimpses_Viewholder glimpses_viewholder, int i, @NonNull Glimpses glimpses) {
                Picasso.get().load(glimpses.getGlimpimg()).into(glimpses_viewholder.imageView);
            }

            @NonNull
            @Override
            public Glimpses_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Glimpses_Viewholder(LayoutInflater.from(contentView.getContext()).inflate(R.layout.glimpses_card,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

        closeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss bottom sheet
                dismiss();
            }
        });

        //Set the coordinator layout behavior
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }
}