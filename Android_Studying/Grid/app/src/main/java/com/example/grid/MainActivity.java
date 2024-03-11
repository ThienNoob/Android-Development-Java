package com.example.grid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gv_Dish);
        ArrayList<Dish> arrayDish = new ArrayList<Dish>();
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Pizza",R.drawable.pizza));
        arrayDish.add( new Dish("Pizza",R.drawable.pizza));
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Pizza",R.drawable.pizza));

        DishAdapter adapter = new DishAdapter(this,arrayDish);
        gridView.setAdapter(adapter);


    }
}
