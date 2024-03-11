package com.example.lab6_02;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private PowerStateChangeReceiver powerStateChangeReceiver; // Di chuyển biến ra ngoài để có phạm vi truy cập

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo BroadcastReceiver
        powerStateChangeReceiver = new PowerStateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerStateChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy đăng ký BroadcastReceiver khi Activity bị destroy
        if (powerStateChangeReceiver != null) {
            unregisterReceiver(powerStateChangeReceiver);
        }
    }
}
