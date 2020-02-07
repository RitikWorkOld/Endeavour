package com.example.endeavour.BQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.endeavour.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bquiz_intro extends AppCompatActivity {

    public Button get_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bquiz_intro);

        get_started = findViewById( R.id.btn_get_started );

        get_started.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bquiz_intro.this,Bquiz.class);
                startActivity(intent);
                finish();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("BquizStatus").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.keepSynced(true);
                databaseReference.child("status").setValue("closed");
            }
        } );
    }
}
