package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.endeavour.Events_Fragments.Events_Corp;
import com.example.endeavour.Events_Fragments.Events_Fun;
import com.example.endeavour.Events_Fragments.Events_Tech;

public class EventsMain extends AppCompatActivity {

    RadioButton radioButton_tech, radioButton_fun, radioButton_corp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);

        radioButton_tech = (RadioButton)findViewById(R.id.radio_technical);
        radioButton_fun = (RadioButton)findViewById(R.id.radio_fun);
        radioButton_corp = (RadioButton)findViewById(R.id.radio_corporate);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.events_container,new  Events_Fun());
        fragmentTransaction.commit();


        radioButton_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.events_container,new Events_Tech());
                fragmentTransaction1.commit();
            }
        });
        radioButton_fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.events_container,new Events_Fun());
                fragmentTransaction2.commit();
            }
        });
        radioButton_corp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.events_container,new Events_Corp());
                fragmentTransaction3.commit();

            }
        });
    }
}
