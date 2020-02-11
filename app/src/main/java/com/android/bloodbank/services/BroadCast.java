package com.android.bloodbank.services;


import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.bloodbank.R;

public class BroadCast extends BroadcastReceiver {
    NotificationManagerCompat notificationManagerCompat;
    Context context;

    public BroadCast(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noCon;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            noCon = intent.getBooleanExtra(ConnectivityManager
                    .EXTRA_NO_CONNECTIVITY, false);
            if (noCon){
                displayNotification("Disconnected");
//                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }
            else {
                displayNotification("Connected");
//                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void displayNotification(String msg){
        Notification notification = new NotificationCompat
                .Builder(context, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.my_icon_first)
                .setContentTitle("My Notification")
                .setContentText(msg)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1,notification);
    }
}