package com.example.lab6_02;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class PowerConnectionService extends Service {
    private PowerStateChangeReceiver powerStateChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        powerStateChangeReceiver = new PowerStateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerStateChangeReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(powerStateChangeReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
