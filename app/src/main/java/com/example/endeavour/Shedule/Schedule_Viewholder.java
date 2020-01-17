package com.example.endeavour.Shedule;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endeavour.R;

import org.w3c.dom.Text;

public class Schedule_Viewholder extends RecyclerView.ViewHolder {

    public TextView eventtitle;
    public TextView description;
    public TextView venue;
    public TextView time;
    public TextView gotoevents;
    public ImageView eventimage;
    public Boolean expanded;
    public LinearLayout expandable;
    public RelativeLayout expander;

    public Schedule_Viewholder(@NonNull View itemView) {
        super(itemView);

        expanded = false;
        expander = itemView.findViewById(R.id.relativelayout_expander);
        expandable = itemView.findViewById(R.id.linearlayout_expandable);
        eventtitle = itemView.findViewById(R.id.title_ev_sh);
        description = itemView.findViewById(R.id.desc_sh_tv);
        venue = itemView.findViewById(R.id.venue_ev_sh);
        time = itemView.findViewById(R.id.time_ev_sh);
        gotoevents = itemView.findViewById(R.id.tv_go_to_events);
        eventimage = itemView.findViewById(R.id.schedule_event_image);
    }
}