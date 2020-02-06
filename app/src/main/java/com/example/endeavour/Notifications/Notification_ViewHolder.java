package com.example.endeavour.Notifications;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endeavour.R;

import org.w3c.dom.Text;

public class Notification_ViewHolder extends RecyclerView.ViewHolder {

    TextView Title;
    TextView Description;
    TextView Date;
    //ImageView Cancel_btn;
    LinearLayout notification_trigger;

    public Notification_ViewHolder(@NonNull View itemView) {
        super(itemView);

        Title = itemView.findViewById(R.id.tv_noti_title);
        Description = itemView.findViewById(R.id.tv_noti_desc);
        Date=itemView.findViewById( R.id.date );
        //Cancel_btn = itemView.findViewById(R.id.cancel_noti_btn);
        notification_trigger = itemView.findViewById(R.id.notification_trigger);
    }
}
