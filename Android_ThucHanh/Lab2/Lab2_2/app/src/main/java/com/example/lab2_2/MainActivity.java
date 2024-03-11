package com.example.lab2_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Employee> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text_view);
        ListView listView = findViewById(R.id.list_view);
        EditText editTextName = findViewById(R.id.edit_text_name);
        EditText editTextId = findViewById(R.id.edit_text_id);
        RadioButton radioButton = findViewById(R.id.radio_full);
        Button button = findViewById(R.id.button);

//        itemsList.add("Tran");
//        itemsList.add("Chuc");
//        itemsList.add("Thien");

        final ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
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
                Employee newItem;
                if (radioButton.isChecked())
                    newItem = new FulltimeEmployee(editTextName.getText().toString(),editTextId.getText().toString());
                else
                    newItem = new ParttimeEmployee(editTextName.getText().toString(),editTextId.getText().toString());
                itemsList.add(newItem);
                adapter.notifyDataSetChanged();
                editTextId.setText("");
                editTextName.setText("");
            }
        });


    }
}
