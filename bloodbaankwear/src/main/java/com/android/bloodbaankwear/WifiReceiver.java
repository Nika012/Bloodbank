package com.android.bloodbaankwear;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class WifiReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

        if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED) {
            showNotification(context, "Connected to Wi-Fi");

        } else if (wifiStateExtra == WifiManager.WIFI_STATE_DISABLED) {
            showNotification(context, "Disconnected from Wi-Fi");
        }

    }


    private void showNotification(Context context, String message) {
        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "chatmandu")
                .setSmallIcon(R.drawable.ic_rss_feed_black_24dp)
                .setContentTitle("Wi-Fi Connectivity")
                .setContentText(message)
                .extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, notificationBuilder.build());


    }
}
