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

import com.example.endeavour.R;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_events_details,container,false);

        Bundle bundle = getArguments();

        String Title = bundle.getString("Title");
        String Descp = bundle.getString("Descp");
        String Desc1 = bundle.getString("Desc1");
        String Desc2 = bundle.getString("Desc2");
        String Mimguri = bundle.getString("Mimguri");
        String Simguri = bundle.getString("Simguri");
        final String Register_uri = bundle.getString("Register_uri");
        readless = view.findViewById(R.id.read_less_events);

        Title_dt = view.findViewById(R.id.event_title);
        Descp_dt = view.findViewById(R.id.event_descp);
        Desc1_dt = view.findViewById(R.id.event_descp1);
        Desc2_dt = view.findViewById(R.id.event_descp2);
        Mimg_dt = view.findViewById(R.id.event_main_img1);
        Simg_dt = view.findViewById(R.id.event_main_img2);
        Register_dt = view.findViewById(R.id.register_events);

        Title_dt.setText(Title);
        Descp_dt.setText(Descp);
        Desc1_dt.setText(Desc1);
        Desc2_dt.setText(Desc2);
        Picasso.get().load(Mimguri).into(Mimg_dt);
        Picasso.get().load(Simguri).into(Simg_dt);

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
