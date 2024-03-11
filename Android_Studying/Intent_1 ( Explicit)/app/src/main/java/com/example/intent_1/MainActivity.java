package com.example.intent_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String[] array = { "Trần", "Chức", "Thiện"};
                Employee[] a = {    new Employee ("Thiện",20),
                                    new Employee ("Chức",19)
                };

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("data", "Tran Chuc Thien");
                intent.putExtra("data_int", 10);
                intent.putExtra("data_double", 3.14);
                intent.putExtra("data_string_array",array);
                intent.putExtra("data_employee", a);


                Bundle bundle = new Bundle();
                bundle.putString("data", "Tran Chuc Thien");
                bundle.putInt("data_int", 10);
                bundle.putStringArray("data_string_array",array);
                bundle.putSerializable("data_employee", a[0]);
                intent.putExtra("data_Bundle",bundle);



                startActivity(intent);
            }
        });

    }
}