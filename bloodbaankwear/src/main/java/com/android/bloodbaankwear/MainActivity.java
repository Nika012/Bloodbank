package com.android.bloodbaankwear;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

public class MainActivity extends WearableActivity {

    private WifiReceiver wifiReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiReceiver = new WifiReceiver();

        createNotificationChannel();

        // Enables Always-on
        setAmbientEnabled();
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel("chatmandu", "Wi-Fi Status", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription("Wifi Status Notification");

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
    }
}



