package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_demo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getTitle().toString())
       {
           case "Add":
               Toast.makeText(this,"Bạn chọn "+ item.getTitle().toString(),Toast.LENGTH_SHORT).show();
               break;
           case "Search":
               Toast.makeText(this,"Bạn chọn "+ item.getTitle().toString(),Toast.LENGTH_SHORT).show();
               break;
           case "Notifications":
               Toast.makeText(this,"Bạn chọn "+ item.getTitle().toString(),Toast.LENGTH_SHORT).show();
               break;

           default:
               Toast.makeText(this,"Bạn chọn Home " ,Toast.LENGTH_SHORT).show();
               break;
       }

        return super.onOptionsItemSelected(item);
    }
}