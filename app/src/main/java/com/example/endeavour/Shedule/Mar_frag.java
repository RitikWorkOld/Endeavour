package com.example.endeavour.Shedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.endeavour.R;

public class Mar_frag extends Fragment {

    private LinearLayout february;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mar_frag,container,false);

        february = (LinearLayout)view.findViewById(R.id.february);

        february.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Feb_frag feb_frag = new Feb_frag();

                fragmentTransaction.replace(R.id.container_shedule,feb_frag);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
