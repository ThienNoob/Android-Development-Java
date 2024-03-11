package com.example.lab2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Employee> itemsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText_id = (EditText)findViewById(R.id.edit_text_id);
        EditText editText_fullname = (EditText)findViewById(R.id.edit_text_fullname);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);

        ListView listView = (ListView) findViewById(R.id.list_item);
        Button button = (Button)findViewById(R.id.button);

        final EmployeeAdapter adapter = new EmployeeAdapter(this, android.R.layout.simple_list_item_1, itemsList);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee newItem ;
                if (checkBox.isChecked())
                {
                    newItem = new Employee(editText_id.getText().toString(),editText_fullname.getText().toString(),true);
                }
                else
                    newItem = new Employee(editText_id.getText().toString(),editText_fullname.getText().toString(),false);
                itemsList.add(newItem);
                adapter.notifyDataSetChanged();

            }
        });
    }


}