package com.example.spinner_custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Spinner sp_Dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp_Dish = (Spinner) findViewById(R.id.sp_Dish);
        ArrayList<Dish> arrayDish = new ArrayList<Dish>();
        arrayDish.add(new Dish(R.drawable.bundau,"Bún đậu"));
        arrayDish.add(new Dish(R.drawable.pizza,"Pizza"));
        DishAdapter dishAdapter = new DishAdapter(this,R.layout.item_layout,arrayDish);
        sp_Dish.setAdapter(dishAdapter);
        sp_Dish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy giá trị mục được chọn
                Dish selectedDish = arrayDish.get(position);

                // Hiển thị giá trị của mục đã chọn trong TextView
                // và ẩn ImageView
                TextView textView = view.findViewById(R.id.tv_dish_name); // Lấy TextView từ view
                ImageView imageView = view.findViewById(R.id.iv_dish_image); // Lấy ImageView từ view
                textView.setText(selectedDish.Name);
                imageView.setVisibility(View.GONE); // Ẩn ImageView

                // Cập nhật Spinner để hiển thị giá trị đã chọn
                sp_Dish.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}