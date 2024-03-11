package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button btnBack=findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v->{
            Intent iMainActivity=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(iMainActivity);
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        });

    }
}