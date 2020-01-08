package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Reg_Sucess extends AppCompatActivity {

    Button loginbtn,OTP;
    int code=91;
    ImageView crossiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__sucess);

        loginbtn = (Button)findViewById(R.id.login_btn_rs);
        crossiv = (ImageView) findViewById(R.id.cross_btn_rs);
        OTP = (Button)findViewById(R.id.login_btn_rs2);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reg_Sucess.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        crossiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reg_Sucess.this,RegAct.class);
                startActivity(intent);
                finish();
            }
        });
        OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();         //added
                String mobile = i.getStringExtra("mobile");           //added

                Intent intent = new Intent(Reg_Sucess.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                    return;
            }
        });
    }
}
