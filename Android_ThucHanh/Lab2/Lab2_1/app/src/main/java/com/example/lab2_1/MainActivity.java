package com.example.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"Tran","Chuc", "Thien"};
        TextView textView= (TextView)findViewById(R.id.text_view);

        ListView listView = (ListView)findViewById(R.id.list_view);

        Adapter adapter =new Adapter(this,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                textView.setText("position : " + position + " ; value = " + items[position]);


            }
        });






    }
}