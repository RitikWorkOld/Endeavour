package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventsFragments extends Fragment {
    private EventAdapter mAdapter;
    private ArrayList<ModelEvent> arrayList;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private FirebaseRecyclerOptions<ModelEvent> options;



    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton,mRadioButton1,mRadioButton2;
    private DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;

    private LinearLayout mLayoutNoItem,mLayoutItem;





    public EventsFragments() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_event,container,false);
        if (firebaseDatabase == null) {
            firebaseDatabase=FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }

        //INITIALIZATION
        mRecyclerView = view.findViewById(R.id.recycler_view_events);
        mRadioGroup = view.findViewById(R.id.radio_group);
        mRadioButton1 = view.findViewById(R.id.radio_fun);
        mRadioButton1.setChecked(true);
        mLayoutItem = view.findViewById(R.id.layout_events);
        mLayoutNoItem = view.findViewById(R.id.layout_no_events);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("events");
        databaseReference.keepSynced(true);
        arrayList = new ArrayList<ModelEvent>();

        options = new FirebaseRecyclerOptions.Builder<ModelEvent>().setQuery(databaseReference,ModelEvent.class).build();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mRadioButton = group.findViewById(checkedId);
                if(mRadioButton != null && checkedId > -1){
                    if(mRadioButton.getText().equals("Fun")){
                        setData(1); //1 for Fun Events
                    }else if(mRadioButton.getText().equals("Technical")){
                        setData(2); //2 for Technical events
                    }
                    else if(mRadioButton.getText().equals("Corporate")){
                        setData(3); //3 for corporate events
                    }
                }
            }
        });


        setData(1);

        return view;
    }
    private void setData(final int pos) {


        if (pos == 1) {
            //UPCOMING
            if (arr1.length() == 0) {
                mLayoutItem.setVisibility(View.GONE);
                mLayoutNoItem.setVisibility(View.VISIBLE);
            } else {
            }


        }
    }









}
