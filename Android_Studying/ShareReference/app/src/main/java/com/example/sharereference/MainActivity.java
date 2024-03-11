package com.example.sharereference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextUsername,editTextPassword;
    Button buttonLogin;
    CheckBox checkBox;
    boolean rememberme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String USERNAME_KEY = "user";
    String USERPASS_KEY = "pass";
    String IsLogin_KEY = "isLogin";

    // Key để lưu trạng thái ô nhớ tôi trong SharedPreferences

    boolean isLogin;
    void findViewByIds()
    {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        checkBox = ( CheckBox) findViewById(R.id.checkbox);
        sharedPreferences = getSharedPreferences("loginreference",MODE_PRIVATE); // tên file reference


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();
        SetFromShareReference();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUsername.getText().toString().trim();
                String pass = editTextPassword.getText().toString();

                if(user.equals("thien") && pass.equals("000"))
                {
                    Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    if(checkBox.isChecked()==true)
                    {
                        isLogin = true;
                        editor = sharedPreferences.edit();
                        editor.putString(USERNAME_KEY,user);
                        editor.putString(USERPASS_KEY,pass);
                        editor.putBoolean(IsLogin_KEY,isLogin);
                        editor.commit();

                    }

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    void SetFromShareReference()
    {
        editTextUsername.setText(sharedPreferences.getString(USERNAME_KEY, ""));
        editTextPassword.setText(sharedPreferences.getString(USERPASS_KEY, ""));
        if (editTextPassword.getText().toString() != "")
            checkBox.setChecked(true);
        if(sharedPreferences.getBoolean(IsLogin_KEY,false)==true)
        {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }


    }

}