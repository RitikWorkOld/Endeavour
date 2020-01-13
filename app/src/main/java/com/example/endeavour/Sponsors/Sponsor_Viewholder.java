package com.example.endeavour.Sponsors;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endeavour.R;

public class Sponsor_Viewholder extends RecyclerView.ViewHolder {

    TextView sponsort;
    TextView sponsorC;
    de.hdodenhof.circleimageview.CircleImageView sponsorImage;

    public Sponsor_Viewholder(@NonNull View itemView) {
        super(itemView);

        sponsort = itemView.findViewById(R.id.tv_sponsor_name);
        sponsorC = itemView.findViewById(R.id.tv_sponsor_domain);
        sponsorImage = itemView.findViewById(R.id.image_sponsor);
    }
}