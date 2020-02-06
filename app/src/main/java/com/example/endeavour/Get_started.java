package com.example.endeavour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Get_started extends AppCompatActivity {

    Button btn;
    List<User_1> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_get_started );

        lst=new ArrayList<>(  );
        lst.add(new User_1( "Timeliner",R.drawable.time ));
        lst.add(new User_1( "SpringEdge",R.drawable.spring_edge ));
        lst.add(new User_1( "Du beat",R.drawable.du_beats ));
        lst.add(new User_1( "The Souled Store",R.drawable.souled_store ));
        lst.add(new User_1( "SpringEdge",R.drawable.spring_edge ));
        lst.add(new User_1( "Du beat",R.drawable.du_beats ));
        lst.add(new User_1( "The Souled Store",R.drawable.souled_store ));
        lst.add(new User_1( "SpringEdge",R.drawable.spring_edge ));
        lst.add(new User_1( "Du beat",R.drawable.du_beats ));
        lst.add(new User_1( "The Souled Store",R.drawable.souled_store ));
        lst.add(new User_1( "SpringEdge",R.drawable.spring_edge ));
        lst.add(new User_1( "Du beat",R.drawable.du_beats ));
        lst.add(new User_1( "The Souled Store",R.drawable.souled_store ));

        RecyclerView myrv=(RecyclerView) findViewById( R.id.recyclerView );
        btn=(Button) findViewById( R.id.g_s_btn ) ;

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Get_started.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        } );

        RecyclerViewAdapter mAdapter=new RecyclerViewAdapter( this,lst );
        myrv.setLayoutManager(new GridLayoutManager( this,2 ) );
myrv.setAdapter( mAdapter );
    }
}
