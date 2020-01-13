package com.example.endeavour.Notifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.endeavour.Customised.BucketRecyclerView;
import com.example.endeavour.Dashboard;
import com.example.endeavour.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Notifications extends AppCompatActivity {

    private BucketRecyclerView recyclerView;
    private ArrayList<Noti_Helper> arrayList;
    private FirebaseRecyclerOptions<Noti_Helper> options;
    private FirebaseRecyclerAdapter<Noti_Helper,Notification_ViewHolder> adapter;
    private DatabaseReference databaseReference;
    private View no_new_notifications;
    private ImageView back_btn;

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        no_new_notifications = findViewById(R.id.no_new_notifications);
        back_btn = findViewById(R.id.back_btn_noti);

        recyclerView = findViewById(R.id.noti_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.showIfEmpty(no_new_notifications);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("notification")
        .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.keepSynced(true);

        arrayList = new ArrayList<Noti_Helper>();

        options = new FirebaseRecyclerOptions.Builder<Noti_Helper>().setQuery(databaseReference,Noti_Helper.class).build();

        adapter = new FirebaseRecyclerAdapter<Noti_Helper, Notification_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Notification_ViewHolder notification_viewHolder, int i, @NonNull Noti_Helper noti_helper) {

                notification_viewHolder.Title.setText(noti_helper.getTitle());
                notification_viewHolder.Description.setText(noti_helper.getDesc());

                final String notiid = noti_helper.getNotiid();

                notification_viewHolder.Cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase.getInstance().getReference().child("notification")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(notiid).removeValue();
                        //Toast.makeText(Notifications.this,"Sucess "+notiid,Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @NonNull
            @Override
            public Notification_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new Notification_ViewHolder(LayoutInflater.from(Notifications.this).inflate(R.layout.notification_card,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notifications.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
