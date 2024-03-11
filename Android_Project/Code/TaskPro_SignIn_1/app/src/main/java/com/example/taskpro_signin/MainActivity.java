package com.example.taskpro_signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     Button signInButton, signUpButton;
     TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = (Button) findViewById(R.id.signInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        textView = (TextView) findViewById(R.id.textView2);


        signInButton.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, SignIn.class));
           }
        });
        signUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://support.google.com/google-ads/answer/12929169?hl=vi";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Nếu không có ứng dụng nào xử lý URL, có thể thực hiện xử lý tùy chỉnh ở đây
                    Toast.makeText(getApplicationContext(), "Web browser not found.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}