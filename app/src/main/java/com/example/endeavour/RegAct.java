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

import com.example.endeavour.Utils.Save;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class RegAct extends AppCompatActivity implements View.OnClickListener {

    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText editTextCode;

    //firebase auth object
    private FirebaseAuth mAuth;
    private String endvr = "ENDVR";



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
        findViewById(R.id.btnrequestotp).setOnClickListener(this);//**************************



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

    /*private void registerUser() {

        final String email=emailId.getText().toString().trim();
        final String pwd=password.getText().toString().trim();
        final String fname=fname1.getText().toString().trim();
        final String branch=branch1.getText().toString().trim();
        final String year=year1.getText().toString().trim();
        final String cid=cid1.getText().toString().trim();
        final String number=number1.getText().toString().trim();
        final String cname=cname1.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);
        mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String refrelid = endvr.concat(number);
                    String uid = FirebaseAuth.getInstance().getUid();
                    User user=new User(fname,email,branch,year,cid,number,cname,uid,refrelid);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        //saving session
                                        Save.save(getApplicationContext(),"session","true");

                                        //Toast.makeText(RegAct.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(RegAct.this,Reg_Sucess.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Intent intent = new Intent(RegAct.this,Reg_Fail.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                }
                else {
                    progressBar.setVisibility(View.GONE);
                    //Toast.makeText(RegAct.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegAct.this,Reg_Fail.class);
                    startActivity(intent);
                }
            }
        });
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnrequestotp:
                progressBar.setVisibility(View.VISIBLE);
                findViewById(R.id.btnrequestotp).setVisibility(View.GONE);
                boolean valid = validateUser();
                if (valid){
                    final String number=number1.getText().toString().trim();
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Users");
                    dbref.keepSynced(true);
                    dbref.orderByChild("contactN").equalTo(number).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null){
                                progressBar.setVisibility(View.GONE);
                                findViewById(R.id.btnrequestotp).setVisibility(View.VISIBLE);
                                Toast.makeText(RegAct.this,"User on this phone Number Already Exists",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                final String email=emailId.getText().toString().trim();
                                final String pwd=password.getText().toString().trim();
                                final String fname=fname1.getText().toString().trim();
                                final String branch=branch1.getText().toString().trim();
                                final String year=year1.getText().toString().trim();
                                final String cid=cid1.getText().toString().trim();
                                final String number=number1.getText().toString().trim();
                                final String cname=cname1.getText().toString().trim();

                                Intent intent = new Intent(RegAct.this,RequestOtp.class);
                                intent.putExtra("name",fname);
                                intent.putExtra("email",email);
                                intent.putExtra("password",pwd);
                                intent.putExtra("branch",branch);
                                intent.putExtra("year",year);
                                intent.putExtra("campusid",cid);
                                intent.putExtra("number",number);
                                intent.putExtra("cname",cname);
                                startActivity(intent);

                                progressBar.setVisibility(View.GONE);
                                findViewById(R.id.btnrequestotp).setVisibility(View.VISIBLE);
                                //Toast.makeText(RegAct.this,"NO user found",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    findViewById(R.id.btnrequestotp).setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private boolean validateUser() {
        final String email=emailId.getText().toString().trim();
        final String pwd=password.getText().toString().trim();
        final String fname=fname1.getText().toString().trim();
        final String branch=branch1.getText().toString().trim();
        final String year=year1.getText().toString().trim();
        final String cid=cid1.getText().toString().trim();
        final String number=number1.getText().toString().trim();
        final String cname=cname1.getText().toString().trim();

        if(fname.isEmpty()){
            fname1.setError(getString(R.string.input_error_name));
            fname1.requestFocus();
            return false;
        }
        else if(email.isEmpty()){
            emailId.setError(getString(R.string.input_error_email));
            emailId.requestFocus();
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailId.setError(getString(R.string.input_error_email_invalid));
            emailId.requestFocus();
            return false;
        }
        else if(pwd.isEmpty()){
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return false;
        }
        else if (pwd.length() < 6 ) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(pwd).matches()){

            password.setError("1 Digit? \n 1 LowerCase? \n 1 UpperCase? \n 1 Special Character? \n atleast 6 character?");
            password.requestFocus();
            return false;
        }

        else if(branch.isEmpty()){
            branch1.setError("Please Enter Branch");
            branch1.requestFocus();
            return false;
        }

        else if(year.isEmpty()){
            year1.setError("Please Enter Year");
            year1.requestFocus();
            return false;
        }
        else if(cid.isEmpty()){
            cid1.setError("Please Enter CampusID");
            cid1.requestFocus();
            return false;
        }
        else if(number.isEmpty()){
            number1.setError("Please Enter Your Number");
            number1.requestFocus();
            return false;
        }

        else if (number.length() != 10) {
            number1.setError(getString(R.string.input_error_phone_invalid));
            number1.requestFocus();
            return false;
        }
        else if(cname.isEmpty()){
            cname1.setError("Please Enter College Name");
            cname1.requestFocus();
            return false;
        }

        else {
            return true;
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




