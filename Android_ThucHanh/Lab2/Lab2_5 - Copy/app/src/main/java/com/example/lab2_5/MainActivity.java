package com.example.lab2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner sp_thumbnail;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp_thumbnail = (Spinner) findViewById(R.id.sp_thumbnail);
        int[] array = { R.drawable.bundau,R.drawable.comtam, R.drawable.garan, R.drawable.pizza};
        ThumbnailAdapter adapter = new ThumbnailAdapter(this,R.layout.thumbnail_layout,array);
        sp_thumbnail.setAdapter(adapter);
        sp_thumbnail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int n = array[position];
                TextView sp_thumbnail_tv_dish_name = (TextView) findViewById(R.id.sp_thumbnail_tv_dish_name);
                ImageView sp_thumbnail_iv_dish_image = (ImageView) findViewById(R.id.sp_thumbnail_iv_dish_image);
                sp_thumbnail_tv_dish_name.setText("Thumbnail "+(position + 1 ));
                sp_thumbnail_iv_dish_image.setVisibility(View.GONE);
                sp_thumbnail.setSelection(position);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // đổ Grid view
        gridView = (GridView) findViewById(R.id.gv_Dish);
        ArrayList<Dish> arrayDish = new ArrayList<Dish>();
        arrayDish.add( new Dish("Bún đậu",R.drawable.bundau));
        arrayDish.add( new Dish("Pizza",R.drawable.pizza));
        arrayDish.add( new Dish("Gà rán",R.drawable.garan));
        arrayDish.add( new Dish("Cơm tấm",R.drawable.comtam));

        DishAdapter adapterDish = new DishAdapter(this,arrayDish);
        gridView.setAdapter(adapterDish);

        // xử lí button
        Button btn_add = (Button) findViewById(R.id.btn_add);
        EditText et_name= (EditText)findViewById(R.id.et_name);
        CheckBox cb_promotion = (CheckBox) findViewById(R.id.cb_promotion);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giá trị từ EditText và Spinner
                String dishName = et_name.getText().toString();
                int spinnerPosition = sp_thumbnail.getSelectedItemPosition();

                // Tạo một món mới và thêm vào danh sách
                Dish newDish = new Dish(dishName, array[spinnerPosition]);
                arrayDish.add(newDish);

                // Cập nhật GridView
                adapterDish.notifyDataSetChanged();

                // Hiển thị thông báo Toast với tên món ăn
                Toast.makeText(MainActivity.this, "Đã thêm món: " + dishName, Toast.LENGTH_SHORT).show();

                // Xóa nội dung của EditText
                et_name.setText("");
                sp_thumbnail.setSelection(0);
                cb_promotion.setChecked(false);

            }
        });










    }
}