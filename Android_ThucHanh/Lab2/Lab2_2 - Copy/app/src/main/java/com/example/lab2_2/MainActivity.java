package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private List<String> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        ListView listView = findViewById(R.id.list_view);
        EditText editText = findViewById(R.id.edit_text);
        Button button = findViewById(R.id.button);

        itemsList.add("Tran");
        itemsList.add("Chuc");
        itemsList.add("Thien");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText("position : " + position + " ; value = " + itemsList.get(position));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                itemsList.remove(position);
                adapter.notifyDataSetChanged();
                return false;


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                itemsList.add(newItem);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });


    }
}
