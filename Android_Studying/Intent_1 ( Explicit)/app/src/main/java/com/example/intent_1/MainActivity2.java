package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        String text = "";
        String[] array = intent.getStringArrayExtra("data_string_array");
        Employee[] a = (Employee[]) intent.getSerializableExtra("data_employee");
        text = intent.getStringExtra("data") + "\n"
                + intent.getIntExtra("data_int",0) + "\n"
                + intent.getDoubleExtra("data_double",0)+ "\n"
                + array[2] + "\n"
                + a[1].name + " " + a[1].age ;
        textView.setText(text);
    }
}