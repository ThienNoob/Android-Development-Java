package com.example.lab6_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBroadcastReceiver();
        registerReceiver(broadcastReceiver, filter);
    }




    private void initBroadcastReceiver() {
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }
    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, "You have a new message", Toast.LENGTH_LONG).show();

        TextView tvContent = findViewById(R.id.tv_content);

        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
            if (messages != null) {
                StringBuilder sms = new StringBuilder(tvContent.getText().toString());

                SmsMessage smsMsg;
                for (Object message : messages) {
                    smsMsg = SmsMessage.createFromPdu((byte[]) message);

                    String msgBody = smsMsg.getMessageBody();
                    String address = smsMsg.getDisplayOriginatingAddress();
                    sms.append(address).append(":\n").append(msgBody).append("\n\n");
                }

                tvContent.setText(sms.toString());
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }
}