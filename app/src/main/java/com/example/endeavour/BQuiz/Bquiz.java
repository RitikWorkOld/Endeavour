package com.example.endeavour.BQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.endeavour.Events_Fragments.Events_Fun;
import com.example.endeavour.R;

public class Bquiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bquiz);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_bquiz,new fr_bquiz_intro());
        fragmentTransaction.commit();

    }
}
