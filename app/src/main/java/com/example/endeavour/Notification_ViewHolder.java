package com.example.endeavour;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Notification_ViewHolder extends RecyclerView.ViewHolder {

    TextView Title;
    TextView Description;
    ImageView Cancel_btn;

    public Notification_ViewHolder(@NonNull View itemView) {
        super(itemView);

        Title = itemView.findViewById(R.id.tv_noti_title);
        Description = itemView.findViewById(R.id.tv_noti_desc);
        Cancel_btn = itemView.findViewById(R.id.cancel_noti_btn);
    }
}
