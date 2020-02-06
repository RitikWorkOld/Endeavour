package com.example.endeavour.BQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.endeavour.Events_Fragments.Events_Fun;
import com.example.endeavour.R;

public class Bquiz extends AppCompatActivity {

    public TextView timer;

    public CountDownTimer countDownTimer;
    public long totaltime = 600000;//10 mins.....

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_bquiz);

        timer = (TextView)findViewById(R.id.time_score);

        countDownTimer = new CountDownTimer(totaltime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                totaltime = millisUntilFinished;
                updatetimer();
            }

            @Override
            public void onFinish() {

                Toast.makeText(Bquiz.this,"TTime up",Toast.LENGTH_SHORT).show();

            }
        }.start();

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_bquiz,new fr_bquiz_intro());
        fragmentTransaction.commit();
    }

    private void updatetimer() {
        int minutes = (int) totaltime / 60000;
        int seconds = (int) totaltime % 60000 / 1000;

        String timelefttext;
        timelefttext = "" + minutes;
        timelefttext += ":";
        if (seconds < 10) timelefttext += "0";
        timelefttext += seconds;

        timer.setText(timelefttext);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(Bquiz.this,"You Can Not Exit At This Moment",Toast.LENGTH_SHORT).show();

    }
}
