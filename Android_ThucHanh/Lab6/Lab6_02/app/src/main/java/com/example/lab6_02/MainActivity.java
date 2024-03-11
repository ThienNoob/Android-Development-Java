package com.example.lab6_02;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

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

        Log.d("MainActivity", "onDestroy called");
        super.onDestroy();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerStateChangeReceiver, intentFilter);
        // Đăng ký lại BroadcastReceiver nếu ứng dụng bị hủy

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop called");
        // Đăng ký lại BroadcastReceiver nếu ứng dụng bị hủy
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerStateChangeReceiver, intentFilter);
    }
}
