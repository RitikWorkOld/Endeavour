package com.example.endeavour;


import android.app.Notification;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.CaseMap;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getBody());
    }
    public void showNotification(String message){

        PendingIntent pi= PendingIntent.getActivity(this,0,new Intent(this, splashAct.class),0);
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"MyNotification")

                .setContentTitle("")
                .setSmallIcon(R.drawable.notification)
                .setAutoCancel(true)
                .setContentText(message)
                .setContentIntent(pi);



        NotificationManagerCompat manager= NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());
    }
}