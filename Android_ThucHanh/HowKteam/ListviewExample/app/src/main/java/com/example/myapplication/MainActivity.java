package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private  ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] items = {"Thien", "Tran", "Chuc"};
        ListView listView= (ListView)findViewById(R.id.list_view);
        DataAdapter dataAdapter = new DataAdapter(this,items);
        listView.setAdapter(dataAdapter);

    }
}