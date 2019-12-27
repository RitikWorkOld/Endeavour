package com.example.endeavour;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class Event_Viewholder extends RecyclerView.ViewHolder {

    TextView eName;
    TextView eTime;
    TextView eVenue;
    TextView eDisc;
    ImageView Imguri;


    public RelativeLayout relativeLayout;


    public Event_Viewholder(@NonNull View itemView) {
        super(itemView);


        eName = itemView.findViewById(R.id.tv_event_title);
        eTime = itemView.findViewById(R.id.tv_event_time);
        eVenue = itemView.findViewById(R.id.tv_event_venue);
        eDisc = itemView.findViewById(R.id.tv_event_desc);
        Imguri = itemView.findViewById(R.id.event_image);

        relativeLayout = itemView.findViewById(R.id.rlayout1);

    }
}