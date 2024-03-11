package com.example.intent;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.security.Provider;

public class ServiceExample extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return  null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.e("Kteam","Service đã được khởi tạo");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Kteam", "Service da duoc huy");
    }
}
