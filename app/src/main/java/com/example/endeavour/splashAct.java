package com.example.endeavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class splashAct extends AppCompatActivity {

    Button splash_btn;
    ImageView endlogo;
    TextView endtext;
    TextView enddesc;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//Noti
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }
        //yahan tak
        splash_btn = (Button) findViewById(R.id.btn_splash);
        endlogo = (ImageView) findViewById(R.id.imageView);
        endtext = (TextView) findViewById(R.id.textView);
        enddesc = (TextView) findViewById(R.id.textView2);
        animationView = (LottieAnimationView) findViewById(R.id.animsplash);

        endtext.setAlpha(0.0f);
        enddesc.setAlpha(0.0f);
        animationView.setAlpha(0.0f);
        splash_btn.setAlpha(0.0f);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splashanim);
        endlogo.setAnimation(animation);

        final Animation animationtext1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animlefttoright);
        final Animation animationdesc= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animrighttoleft);
        final Animation animationanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animzoomin);
        final Animation animationbtn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animbtnlong);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                endtext.setAnimation(animationtext1);
                endtext.setAlpha(1.0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationtext1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                enddesc.setAnimation(animationdesc);
                enddesc.setAlpha(1.0f);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationdesc.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationView.setAnimation(animationanim);
                animationView.setAlpha(1.0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationanim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_btn.setAnimation(animationbtn);
                splash_btn.setAlpha(1.0f);
                animationView.playAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        splash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharedIntent = new Intent(splashAct.this,LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(splashAct.this,
                        Pair.create(v,"logotransition"));
                startActivity(sharedIntent);
                finish();
            }
        });
    }
}
