package com.example.endeavour;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.endeavour.services.Noti_Helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyMessagingService extends FirebaseMessagingService{

    String TAG = "TEST";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            String key1 = remoteMessage.getData().get("key1").toString();
            String key2 = remoteMessage.getData().get("key2").toString();
            uploadData(key1,key2);

            sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getNotification() != null) {

            sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void uploadData(String keya, String keyb) {

        String notiid = FirebaseDatabase.getInstance().getReference().child("notification")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().getKey();

        Noti_Helper noti_helper = new Noti_Helper(keya,keyb,notiid);

        FirebaseDatabase.getInstance().getReference().child("notification")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(notiid).setValue(noti_helper);
    }


    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }

    private void sendNotification(String title,String messageBody) {

        Intent intent = new Intent(this, Notifications.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 , notificationBuilder.build());
    }
}