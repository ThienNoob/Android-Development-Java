package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent URL = new Intent(Intent.ACTION_VIEW, Uri.parse("http://howkteam.com"));
        startActivity(URL);

//        Intent ServiceIntent = new Intent(this,ServiceExample.class);
//        startActivity(ServiceIntent);

//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent) ;
//            }
//        });
    }


}