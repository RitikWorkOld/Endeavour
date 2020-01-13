package com.example.endeavour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.endeavour.Events_Fragments.EventsMain;
import com.example.endeavour.Notifications.Notifications;
import com.example.endeavour.Shedule.Shedule;
import com.example.endeavour.Speakers.Speakers;
import com.example.endeavour.Sponsors.Sponsor;
import com.example.endeavour.Team.TeamMain;
import com.example.endeavour.Utils.Save;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity  {

    LinearLayout layoutOurteam;
    LinearLayout layoutevents;
    FirebaseAuth firebaseAuth;
    LinearLayout layoutsponsors;
    LinearLayout layoutspeakers;
    LinearLayout layoutshedule;

    private Toast backToast;
    ImageView notification_btn, image_power;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        image_power = (ImageView) findViewById(R.id.image_power);
        notification_btn = (ImageView) findViewById(R.id.iv_notification_btn);

        if(isFirstTime()){
            /*TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.image_power), "Log Out Button", "Use this to signout from you account")
                    .tintTarget(false));*/
            new TapTargetSequence(this).targets(
                    TapTarget.forView(findViewById(R.id.image_power), "Log Out Button", "Use this to signout from you account \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .id(1),
                    TapTarget.forView(findViewById(R.id.iv_notification_btn), "Notification Button", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(2),
                    TapTarget.forView(findViewById(R.id.layout_events), "Check all events", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(3),
                    TapTarget.forView(findViewById(R.id.layout_shedule), "Check events schedule", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(4),
                    TapTarget.forView(findViewById(R.id.layout_speakers), "who's the speaker", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(5),
                    TapTarget.forView(findViewById(R.id.layout_sponsors), "Check our sponsors", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(6),
                    TapTarget.forView(findViewById(R.id.layout_ourteam), "Check out team", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(7),
                    TapTarget.forView(findViewById(R.id.layout_faq), "Question you wants to know.", "This will help you \n (Tap on button to Cancel)")
                            .tintTarget(false)
                            .cancelable(false)
                            .targetCircleColor(R.color.colorPrimaryDark)
                            .id(8)
            ).listener(new TapTargetSequence.Listener() {
                @Override
                public void onSequenceFinish() {
                }

                @Override
                public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                }

                @Override
                public void onSequenceCanceled(TapTarget lastTarget) {

                }
            }).start();
        }

        layoutevents = (LinearLayout) findViewById(R.id.layout_events);
        layoutOurteam = (LinearLayout) findViewById(R.id.layout_ourteam);
        layoutsponsors = (LinearLayout) findViewById(R.id.layout_sponsors);
        layoutspeakers = (LinearLayout) findViewById(R.id.layout_speakers);
        layoutshedule = (LinearLayout) findViewById(R.id.layout_shedule);

        firebaseAuth = firebaseAuth.getInstance();

        layoutevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_events = new Intent(Dashboard.this, EventsMain.class);
                startActivity(intent_events);
            }
        });
        layoutsponsors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sponsors = new Intent(Dashboard.this, Sponsor.class);
                startActivity(intent_sponsors);
            }
        });
        layoutspeakers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_speakers = new Intent(Dashboard.this, Speakers.class);
                startActivity(intent_speakers);
            }
        });
        layoutshedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_shedule = new Intent(Dashboard.this, Shedule.class);
                startActivity(intent_shedule);
            }
        });
        image_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                firebaseAuth.getInstance().signOut();
                                //saving session
                                Save.save(getApplicationContext(), "session", "false");
                                Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        layoutOurteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == layoutOurteam) {
                    Intent intent_ourteam = new Intent(Dashboard.this, TeamMain.class);
                    startActivity(intent_ourteam);
                }
            }
        });
        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Notifications.class);
                startActivity(intent);
            }
        });
    }
//---------------------------------------------------------------------------------------
    private boolean isFirstTime() {

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
//------------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finish();
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
