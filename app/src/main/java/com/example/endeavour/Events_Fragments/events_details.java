package com.example.endeavour.Events_Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.endeavour.BQuiz.Bquiz;
import com.example.endeavour.Dashboard;
import com.example.endeavour.R;
import com.example.endeavour.Voting.VotingAct;
import com.example.endeavour.Voting.Voting_helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class events_details extends Fragment {

    TextView Title_dt;
    TextView Descp_dt;
    TextView Desc1_dt;
    TextView Desc2_dt;
    Button Register_dt;
    ImageView Mimg_dt;
    ImageView Simg_dt;
    TextView readless;
    Button gotoquiz;
    Button votenow;
    TextView faq;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events_details,container,false);

        Bundle bundle = getArguments();

        final String Title = bundle.getString("Title");
        String Descp = bundle.getString("Descp");
        String Desc1 = bundle.getString("Desc1");
        String Desc2 = bundle.getString("Desc2");
        String Mimguri = bundle.getString("Mimguri");
        String Simguri = bundle.getString("Simguri");
        final String Register_uri = bundle.getString("Register_uri");
        final String faqid = bundle.getString( "faqid" );
        readless = view.findViewById(R.id.read_less_events);

        Title_dt = view.findViewById(R.id.event_title);
        Descp_dt = view.findViewById(R.id.event_descp);
        Desc1_dt = view.findViewById(R.id.event_descp1);
        Desc2_dt = view.findViewById(R.id.event_descp2);
        Mimg_dt = view.findViewById(R.id.event_main_img1);
        Simg_dt = view.findViewById(R.id.event_main_img2);
        Register_dt = view.findViewById(R.id.register_events);
        gotoquiz = view.findViewById( R.id.gotoquiz );
        votenow = view.findViewById(R.id.votenow);
        faq = view.findViewById( R.id.faq_btn );

        Title_dt.setText(Title);
        Descp_dt.setText(Descp);
        Desc1_dt.setText(Desc1);
        Desc2_dt.setText(Desc2);
        Picasso.get().load(Mimguri).into(Mimg_dt);
        Picasso.get().load(Simguri).into(Simg_dt);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child( "BquizStatus" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() );
        databaseReference.keepSynced( true );
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                QuizUnlocker quizUnlocker = dataSnapshot.getValue(QuizUnlocker.class);

                //Toast.makeText( getActivity().getApplicationContext(),"status  "+quizUnlocker.getStatus().toString(),Toast.LENGTH_SHORT ).show();

                if (quizUnlocker != null){
                    if (quizUnlocker.getStatus().toString().equals( "open" ) && Title.equals( "BizCraft" )){
                        gotoquiz.setVisibility( View.VISIBLE );
                        gotoquiz.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent( getActivity(), Bquiz.class );
                                startActivity( intent );
                            }
                        } );
                    }
                    else {
                        gotoquiz.setVisibility( View.GONE );
                    }
                }
                else {
                    gotoquiz.setVisibility( View.GONE );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );



        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child( "VotingControl" );
        databaseReference1.keepSynced( true );
        databaseReference1.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                VotingUnlocker votingUnlocker = dataSnapshot.getValue(VotingUnlocker.class);

                if (votingUnlocker != null){
                    if (votingUnlocker.getStatus().toString().equals( "open" ) && Title.equals( "Memethon" )){
                        votenow.setVisibility( View.VISIBLE );
                        /*gotoquiz.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent( getActivity(), Bquiz.class );
                                startActivity( intent );
                            }
                        } );*/

                        votenow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("VoteStatus").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Voting_helper voting_helper = dataSnapshot.getValue(Voting_helper.class);

                                        if (voting_helper != null){
                                            if (voting_helper.getVotingstatus().equals("voted")){
                                                Toast.makeText(getContext(),"You have already Voted",Toast.LENGTH_LONG).show();
                                            }
                                            else {
                                                Intent i = new Intent(getContext(), VotingAct.class);
                                                startActivity(i);
                                            }
                                        }
                                        else {
                                            Intent i = new Intent(getContext(), VotingAct.class);
                                            startActivity(i);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                        });


                    }
                    else {
                        votenow.setVisibility( View.GONE );
                    }
                }
                else {
                    votenow.setVisibility( View.GONE );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );




        faq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Bundle bundle = new Bundle();
                bundle.putString( "faqid",faqid);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Faq_events_fargment faq_events_fargment = new Faq_events_fargment();
                faq_events_fargment.setArguments( bundle );

                fragmentTransaction.replace(R.id.events_container,faq_events_fargment);
                fragmentTransaction.addToBackStack("faq");
                fragmentTransaction.commit();
            }
        } );

        Register_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(Register_uri);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        readless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                fragmentManager.popBackStack();
            }
        });
        return view;
    }
}
