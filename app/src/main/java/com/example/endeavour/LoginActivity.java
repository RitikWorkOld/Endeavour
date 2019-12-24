package com.example.endeavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    TextView textView;
    private Toast backToast;
    private long backPressedTime;
    private Button signupbtn;
    FirebaseAuth mFirebaseAuth;
    Button btnSignIn;
    private ProgressBar progressBars;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);



        textView=findViewById(R.id.fpass);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.Lemail);
        password = findViewById(R.id.Lpass);
        signupbtn=findViewById(R.id.button_signup);
        btnSignIn=findViewById(R.id.signin);
        progressBars = findViewById(R.id.progressBar2);
        progressBars.setVisibility(View.GONE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotPass.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == signupbtn)
                {
                    Intent intent=new Intent(LoginActivity.this,RegAct.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();

                }
            }
        });
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
               if(mFirebaseUser==null){
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBars.setVisibility(View.VISIBLE);

                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    progressBars.setVisibility(View.GONE);
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    progressBars.setVisibility(View.GONE);
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    progressBars.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    //check this runs
                    Log.d("LOOP 1", "status: login ");//ye lga rhndo

                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                progressBars.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressBars.setVisibility(View.GONE);

                                Log.d(">> NOTWORKING 1", "onComplete: + COME IN LOOP ");
                                ////yha bhi aaya run statement...ok
                                Intent intToHome = new Intent(getApplicationContext(),Dashboard.class);//not working TEAM.
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    progressBars.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this,"Error Occurred!", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }



//see no logs.. let me give you example of testing
@Override
public void onBackPressed() {


    if (backPressedTime + 2000 > System.currentTimeMillis()) {
        backToast.cancel();
        super.onBackPressed();
        finish();
    } else {
        backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
        backToast.show();


    }
    backPressedTime = System.currentTimeMillis();

}

}