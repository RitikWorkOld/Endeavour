package com.example.endeavour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private List<ModelEvent> mList;
    private Context mContext;
    private ModelEvent mModel;


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvEventTitle,tvEventTime,tvEventVenue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventTitle = itemView.findViewById(R.id.tv_event_title);
            tvEventTime = itemView.findViewById(R.id.tv_event_time);
            tvEventVenue = itemView.findViewById(R.id.tv_event_venue);

        }
    }

    EventAdapter(List<ModelEvent> list, Context context){
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_event_card, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mModel = mList.get(position);
        holder.tvEventTitle.setText(mModel.getmTitle());
        holder.tvEventTime.setText(mModel.getmTime());
        holder.tvEventVenue.setText(mModel.getmVenue());

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}