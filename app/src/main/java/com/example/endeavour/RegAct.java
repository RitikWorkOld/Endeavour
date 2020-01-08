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
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegAct extends AppCompatActivity implements View.OnClickListener {

    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText editTextCode;

    //firebase auth object
    private FirebaseAuth mAuth;


    private EditText emailId,password,fname1,branch1,year1,cid1,number1,cname1;
    private Button loginbtn;
    private Toast backToast;
    private long backPressedTime;
    private ProgressBar progressBar;
    int code=91;

    FirebaseAuth mFirebaseAuth;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reg);
        mFirebaseAuth = FirebaseAuth.getInstance();

        //initializing objects
        mAuth = FirebaseAuth.getInstance();         //added
        editTextCode = findViewById(R.id.editTextCode);             //added

        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        fname1 = findViewById(R.id.fname);
        branch1 = findViewById(R.id.branch);
        year1 = findViewById(R.id.year);
        cid1 = findViewById(R.id.campus);
        number1 = findViewById(R.id.cnumber);
        cname1 = findViewById(R.id.cname);

        loginbtn=(Button) findViewById(R.id.button_login);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.btnsignup).setOnClickListener(this);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == loginbtn)
                {
                    Intent intent=new Intent(RegAct.this,LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();

                }
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mFirebaseAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }




       private void registerUser() {
               final String email=emailId.getText().toString().trim();
                String pwd=password.getText().toString().trim();
           final String fname=fname1.getText().toString().trim();
           final  String branch=branch1.getText().toString().trim();
           final   String year=year1.getText().toString().trim();
           final   String cid=cid1.getText().toString().trim();
           final   String number=number1.getText().toString().trim();
           final   String cname=cname1.getText().toString().trim();



           if(fname.isEmpty()){
               fname1.setError(getString(R.string.input_error_name));
               fname1.requestFocus();
               return;
           }
                else if(email.isEmpty()){
                    emailId.setError(getString(R.string.input_error_email));
                    emailId.requestFocus();
                    return;
                }
           if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
               emailId.setError(getString(R.string.input_error_email_invalid));
               emailId.requestFocus();
               return;
           }
                else if(pwd.isEmpty()){
                    password.setError(getString(R.string.input_error_password));
                    password.requestFocus();
                }
           if (pwd.length() < 6 ) {
               password.setError(getString(R.string.input_error_password_length));
               password.requestFocus();
               return;
           }
           if(!PASSWORD_PATTERN.matcher(pwd).matches()){

               password.setError("1 Digit? \n 1 LowerCase? \n 1 UpperCase? \n 1 Special Character? \n atleast 6 character?");
               password.requestFocus();
               return;
           }



                else if(branch.isEmpty()){
                    branch1.setError("Please Enter Branch");
                    branch1.requestFocus();
                    return;
                }

                else if(year.isEmpty()){
                    year1.setError("Please Enter Year");
                    year1.requestFocus();
                    return;
                }
                else if(cid.isEmpty()){
                    cid1.setError("Please Enter CampusID");
                    cid1.requestFocus();
                    return;
                }
                else if(number.isEmpty()){
                    number1.setError("Please Enter Your Number");
                    number1.requestFocus();
                    return;
                }
           if (number.length() != 10) {
               number1.setError(getString(R.string.input_error_phone_invalid));
               number1.requestFocus();
               return;
           }
                else if(cname.isEmpty()){
                    cname1.setError("Please Enter College Name");
                    cname1.requestFocus();
                    return;
                }


           progressBar.setVisibility(View.VISIBLE);
                mFirebaseAuth.createUserWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                            User user=new User(fname,email,branch,year,cid,number,cname);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                //Toast.makeText(RegAct.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(RegAct.this,Reg_Sucess.class);
                                                String phonenumber="+"+code+number;
                                                intent.putExtra("mobile",phonenumber);
                                                startActivity(intent);
                                            } else {
                                                Intent intent = new Intent(RegAct.this,Reg_Fail.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    //Toast.makeText(RegAct.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegAct.this,Reg_Fail.class);
                                    startActivity(intent);

                                }
                            }
                        });

            }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsignup:
                registerUser();
                break;
        }
    }

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

