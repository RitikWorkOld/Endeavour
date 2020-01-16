package com.example.endeavour.Shedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endeavour.R;

public class Feb_frag extends Fragment {

    private LinearLayout march;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private Boolean expanded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.feb_frag,container,false);

        march = (LinearLayout)view.findViewById(R.id.march);
        linearLayout = (LinearLayout)view.findViewById(R.id.linearlayout);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.relativelayout);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expanded == false){
                    linearLayout.setVisibility(View.VISIBLE);
                    expanded = true;
                }
                else {
                    linearLayout.setVisibility(View.GONE);
                    expanded = false;
                }
            }
        });

        march.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Mar_frag mar_frag = new Mar_frag();

                fragmentTransaction.replace(R.id.container_shedule,mar_frag);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
