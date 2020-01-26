package com.example.endeavour.BQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.endeavour.Events_Fragments.Events_Fun;
import com.example.endeavour.R;

public class Bquiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_bquiz);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_bquiz,new fr_bquiz_intro());
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(Bquiz.this,"You Can Not Exit At This Moment",Toast.LENGTH_SHORT).show();

    }
}
