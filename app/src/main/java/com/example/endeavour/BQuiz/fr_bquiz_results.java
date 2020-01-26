package com.example.endeavour.BQuiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.endeavour.Events_Fragments.EventsMain;
import com.example.endeavour.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fr_bquiz_results extends Fragment {

    private TextView total_ques_count,total_score_count,correct_ans_count,wrong_ans_count,skipped_ques_count;
    private Button exitquiz;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fr_bquiz_results,container,false);

        total_ques_count = view.findViewById( R.id.total_ques_count );
        total_score_count = view.findViewById( R.id.total_score_count );
        correct_ans_count = view.findViewById( R.id.correct_ans_count );
        wrong_ans_count = view.findViewById( R.id.wrong_answers_count );
        skipped_ques_count = view.findViewById( R.id.skipped_ques_count );
        exitquiz = view.findViewById(R.id.exitquizbtn);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "ResultsBquiz" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).child( "Bquiz" );
        databaseReference.orderByChild( "status" ).equalTo( "wrong" ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() != -1){
                    String wrng = String.valueOf( dataSnapshot.getChildrenCount() );
                    wrong_ans_count.setText( wrng );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child( "ResultsBquiz" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).child( "Bquiz" );
        databaseReference1.orderByChild( "status" ).equalTo( "correct" ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() != -1){
                    String correct = String.valueOf( dataSnapshot.getChildrenCount() );
                    correct_ans_count.setText( correct );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        final DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child( "ResultsBquiz" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).child( "Bquiz" );
        databaseReference2.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() != -1){
                    String totalques = String.valueOf( dataSnapshot.getChildrenCount() );
                    total_ques_count.setText( totalques );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        final DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child( "ResultsBquiz" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).child( "Bquiz" );
        databaseReference3.orderByChild( "status" ).equalTo( "skipped" ).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() != -1){
                    String skipped = String.valueOf( dataSnapshot.getChildrenCount() );
                    skipped_ques_count.setText( skipped );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        final DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference().child( "ResultsBquiz" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).child( "Bquiz" );
        databaseReference4.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int scorecount = 0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Score score = dataSnapshot1.getValue(Score.class);
                    scorecount = scorecount + score.getScore();
                }
                total_score_count.setText( String.valueOf( scorecount ) );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        exitquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventsMain.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
