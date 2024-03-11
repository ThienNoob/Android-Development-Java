package com.example.sharereference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity {
    Button button;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String IsLogin_KEY = "isLogin";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("loginreference",MODE_PRIVATE); // tên file reference
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viết hàm thay đổi thông tin isLogin trong share reference thành false
                editor = sharedPreferences.edit();

                // Đặt giá trị isLogin thành false
                editor.putBoolean(IsLogin_KEY, false);

                // Lưu thay đổi vào SharedPreferences
                editor.apply();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                finish(); // Kết thúc HomeActivity khi quay lại MainActivity
            }
        });
    }
}