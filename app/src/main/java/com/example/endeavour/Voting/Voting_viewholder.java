package com.example.endeavour.Voting;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.endeavour.R;

public class Voting_viewholder extends RecyclerView.ViewHolder {

    public TextView team_name,votestatus;
    public ImageView memeimg,votesymbol;

    public Voting_viewholder(@NonNull View itemView) {
        super(itemView);

        memeimg = itemView.findViewById(R.id.memeimg);
        team_name = itemView.findViewById(R.id.team_name_voting);
        votestatus = itemView.findViewById(R.id.upvote);

    }
}
