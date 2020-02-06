package com.example.endeavour.BQuiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.endeavour.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fr_bquiz_intro extends Fragment {

    public Button get_started;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fr_bquiz_intro,container,false);

        get_started = view.findViewById( R.id.btn_get_started );

        get_started.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_bquiz,new fr_bquiz_ques1(),"quiz_time");
                fragmentTransaction.commit();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("BquizStatus").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.keepSynced(true);
                databaseReference.child("status").setValue("closed");
            }
        } );
        return view;
    }
}
