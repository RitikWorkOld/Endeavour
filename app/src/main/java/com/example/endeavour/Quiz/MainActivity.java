package com.example.endeavour.Quiz;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.endeavour.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {


    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView question, timerTxt;
    int total = 0;
    int correct = 0;
    int incorrect = 0;
    int count = -1;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        question = findViewById(R.id.questionText);
        timerTxt = findViewById(R.id.timer);


        updateQuestions();
        reverseTimer(600, timerTxt);
    }

    public void updateQuestions() {
        count++;

        if (count >= 2) {

            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MainActivity.this, Result_activity.class);
            myIntent.putExtra("total", String.valueOf(total));
            myIntent.putExtra("correct", String.valueOf(correct));
            myIntent.putExtra("incorrect",String.valueOf(incorrect));

            startActivity(myIntent);
            finish();

        } else {

            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child( String.valueOf( count ) );
            total++;
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final Questions questions = dataSnapshot.getValue(Questions.class);

                        assert questions != null;
                        question.setText(questions.getQuestions());
                        btn1.setText(questions.getoption1());
                        btn2.setText(questions.getoption2());
                        btn3.setText(questions.getoption3());
                        btn4.setText(questions.getoption4());

                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btn1.getText().toString().equals(questions.answer)) {
                                   // Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                   // btn1.setBackgroundColor(Color.GREEN);
                                    correct++;
                                    updateQuestions();
                                   /* Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            correct++;
                                            btn1.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            updateQuestions();


                                        }
                                    }, 1500);*/
                                } else {
                                   // Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                    incorrect++;
                                  //  btn1.setBackgroundColor(Color.RED);
                                    updateQuestions();
                                   /* if (btn2.getText().toString().equals(questions.getanswer())) {
                                        btn2.setBackgroundColor(Color.GREEN);
                                    } else if (btn3.getText().toString().equals(questions.getanswer())) {
                                        btn3.setBackgroundColor(Color.GREEN);
                                    } else if (btn4.getText().toString().equals(questions.getanswer())) {
                                        btn4.setBackgroundColor(Color.GREEN);



                                    }*/

                                   /* Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            btn1.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn2.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn3.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn4.setBackgroundColor(Color.parseColor("#48B9AC"));

                                            updateQuestions();
                                        }
                                    }, 1500);*/
                                }
                            }


                        });


                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (btn2.getText().toString().equals(questions.getanswer())) {
                                    correct++;
                                    //Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                   // btn2.setBackgroundColor(Color.GREEN);
                                    updateQuestions();
                                  /*  Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {

                                            btn2.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            updateQuestions();


                                        }
                                    }, 2000);*/
                                } else {
                                    incorrect++;
                                 //   Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                 //   btn2.setBackgroundColor(Color.RED);
                                    updateQuestions();
                                 /*   if (btn1.getText().toString().equals(questions.getanswer())) {
                                        btn1.setBackgroundColor(Color.GREEN);
                                    } else if (btn3.getText().toString().equals(questions.getanswer())) {
                                        btn3.setBackgroundColor(Color.GREEN);
                                    } else if (btn4.getText().toString().equals(questions.getanswer())) {
                                        btn4.setBackgroundColor(Color.GREEN);



                                    }*/

                                    /*Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            btn1.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn2.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn3.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn4.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            updateQuestions();
                                        }


                                    }, 1500);*/
                                }
                            }


                        });
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                if (btn3.getText().toString().equals(questions.getanswer())) {
                                    correct++;
                                    updateQuestions();
                                   // Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                   // btn3.setBackgroundColor(Color.GREEN);
                                /*    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {

                                            btn3.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            updateQuestions();


                                        }
                                    }, 2000);*/
                                } else {
                                    incorrect++;
                                   // Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                   // btn3.setBackgroundColor(Color.RED);
                                    updateQuestions();

                                 /*   if (btn1.getText().toString().equals(questions.getanswer())) {
                                        btn1.setBackgroundColor(Color.GREEN);
                                    } else if (btn2.getText().toString().equals(questions.getanswer())) {
                                        btn2.setBackgroundColor(Color.GREEN);
                                    } else if (btn4.getText().toString().equals(questions.getanswer())) {
                                        btn4.setBackgroundColor(Color.GREEN);



                                    }*/

                                  /*  Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            btn1.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn2.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn3.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn4.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            updateQuestions();
                                        }


                                    }, 1500);*/
                                }
                            }


                        });
                        btn4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (btn4.getText().toString().equals(questions.getanswer())) {
                                    correct++;
                                    updateQuestions();
                                   /* Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                                    btn4.setBackgroundColor(Color.GREEN);
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {

                                            btn4.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            updateQuestions();


                                        }
                                    }, 2000);*/
                                } else {
                                    incorrect++;
                                    //Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_SHORT).show();
                                    //btn4.setBackgroundColor(Color.RED);
                                    updateQuestions();
                                   /* if (btn1.getText().toString().equals(questions.getanswer())) {
                                        btn1.setBackgroundColor(Color.GREEN);
                                    } else if (btn2.getText().toString().equals(questions.getanswer())) {
                                        btn2.setBackgroundColor(Color.GREEN);
                                    } else if (btn3.getText().toString().equals(questions.getanswer())) {
                                        btn3.setBackgroundColor(Color.GREEN);


                                    }*/

                                   /* Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {

                                        @Override
                                        public void run() {
                                            btn1.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn2.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn4.setBackgroundColor(Color.parseColor("#48B9AC"));
                                            btn3.setBackgroundColor(Color.parseColor("#48B9AC"));
                                        }


                                    }, 1500);*/
                                }
                            }


                        });
                    }





                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }

    public void reverseTimer(int seconds, final TextView tv) {
        new CountDownTimer(seconds * 1000 + 1000, 1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%2d", minutes) + ":" + String. format("%2d", seconds));
            }


            @SuppressLint("SetTextI18n")
            public void onFinish() {
                tv.setText("Completed");
                Intent myIntent = new Intent(MainActivity.this, Result_activity.class);

                myIntent.putExtra("correct", String.valueOf(correct));
                myIntent.putExtra("incorrect", String.valueOf(incorrect));
                startActivity(myIntent);


            }
        }.start();

    }
}










