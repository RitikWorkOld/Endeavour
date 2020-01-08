package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.messaging.FirebaseMessaging;

public class Dashboard extends AppCompatActivity {

    LinearLayout layoutOurteam;
    LinearLayout layoutevents;
    ImageView notification_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        layoutevents=(LinearLayout) findViewById(R.id.layout_events);
        layoutOurteam = (LinearLayout) findViewById(R.id.layout_ourteam);
        notification_btn = (ImageView) findViewById(R.id.iv_notification_btn);

        FirebaseMessaging.getInstance().subscribeToTopic("USER");

        layoutevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_events= new Intent(Dashboard.this,EventsMain.class);
                startActivity(intent_events);
            }
        });


        layoutOurteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==layoutOurteam) {
                    Intent intent_ourteam = new Intent(Dashboard.this, TeamMain.class);
                    startActivity(intent_ourteam);
                }
            }
        });

        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Notifications.class);
                startActivity(intent);
            }
        });
    }
}
