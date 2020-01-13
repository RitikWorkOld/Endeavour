package com.example.endeavour.Speakers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.endeavour.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.squareup.picasso.Picasso;

public class CardFragment extends Fragment {

    private CardView cardView;
    String TAG = "CARD *****-----";

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.item_viewpager, container, false);

        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        //final TextView title = (TextView) view.findViewById(R.id.title_speak);
        //final TextView description = (TextView)view.findViewById(R.id.desc_speak);

        String position = String.format("%d", getArguments().getInt("position"));

        final TextView title = (TextView) view.findViewById(R.id.title_speak);
        final TextView description = (TextView)view.findViewById(R.id.desc_speak);
        final de.hdodenhof.circleimageview.CircleImageView image_p = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.image_speaker);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("speakers");
        databaseReference.orderByChild("id").equalTo(position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Fall in firebase---------------------------------------------------");
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.d(TAG,"Fall in firebase snapshot ---------------------------------------------------");
                    Speaker_detail speaker_detail = dataSnapshot1.getValue(Speaker_detail.class);
                    String name = speaker_detail.getName();
                    String desc = speaker_detail.getDesc();
                    String imguri = speaker_detail.getImguri();

                    title.setText(name);
                    description.setText(desc);
                    Picasso.get().load(imguri).into(image_p);

                    Log.d(TAG,"Here is name ----------------------------------------  "+name);
                    Log.d(TAG,"Here is desc ----------------------------------------  "+desc);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.d(TAG,"came out ----------------------------------------------------------");
        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
