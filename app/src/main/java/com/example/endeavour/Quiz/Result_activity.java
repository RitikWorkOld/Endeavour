package com.example.endeavour.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.endeavour.R;

public class Result_activity extends AppCompatActivity {

    TextView t1, t2, t3;
    String total, correct, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_result_activity);
        t1 = findViewById(R.id.attempted);
        t2 = findViewById(R.id.correct);
        t3 = findViewById(R.id.incorrect);
        Intent i = getIntent();
        total = i.getStringExtra("total");
        correct = i.getStringExtra("correct");
        incorrect = i.getStringExtra("incorrect");

        setValues();

    }

    private void setValues() {
        t1.setText(total);
        t2.setText(correct);
        t3.setText(incorrect);
    }
}
