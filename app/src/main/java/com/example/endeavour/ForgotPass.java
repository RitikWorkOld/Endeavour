package com.example.endeavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPass extends AppCompatActivity {

    EditText emailId, password;
        EditText userEmail;
    private Toast backToast;
    private long backPressedTime;
        Button fsignin;
    private Button signupbtn;
    Button btnSignIn;
        FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_pass);

        userEmail=findViewById(R.id.edit_email);
        fsignin=findViewById(R.id.submit);
        emailId = findViewById(R.id.Lemail);
        password = findViewById(R.id.Lpass);
        signupbtn=findViewById(R.id.button_signup);
        btnSignIn=findViewById(R.id.signin);

        firebaseAuth=FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if(mFirebaseUser==null){
                    Toast.makeText(ForgotPass.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == signupbtn)
                {
                    Intent intent=new Intent(ForgotPass.this,TeamMain.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();

                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(ForgotPass.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(ForgotPass.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                //Toast.makeText(ForgotPass.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPass.this,Login_Failed.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intToHome = new Intent(ForgotPass.this,TeamMain.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    //Toast.makeText(ForgotPass.this,"Error Occurred!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ForgotPass.this,Login_Failed.class);
                    startActivity(intent);

                }

            }
        });


        fsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                if(email.isEmpty()){
                    userEmail.setError("Please enter email id");
                    userEmail.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    userEmail.setError(getString(R.string.input_error_email_invalid));
                    userEmail.requestFocus();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())

                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {



                        if(task.isSuccessful()){


                            /*Toast.makeText(ForgotPass.this,"Please Check your Registered Email.Please " +
                                            "check SPAM as well as IMPORTANT Emails"

                                    ,Toast.LENGTH_LONG).show();*/
                            Intent intent = new Intent(ForgotPass.this,Forgotpass_Success.class);
                            startActivity(intent);


                        }

                        else{

                            Toast.makeText(ForgotPass.this,task.getException().getMessage()
                                    ,Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }


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
