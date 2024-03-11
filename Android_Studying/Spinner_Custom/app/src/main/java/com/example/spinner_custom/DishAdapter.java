package com.example.spinner_custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    List<Dish> arrayDish;

    public DishAdapter(Context context, int myLayout, List<Dish> arrayDish) {
        this.context = context;
        this.myLayout = myLayout;
        this.arrayDish = arrayDish;
    }

    @Override
    public int getCount() {
        return arrayDish.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(myLayout,null);
        TextView tv_dish_name = (TextView) convertView.findViewById(R.id.tv_dish_name);
        ImageView iv_dish = (ImageView) convertView.findViewById(R.id.iv_dish_image);
        tv_dish_name.setText(arrayDish.get(position).Name);
        iv_dish.setImageResource(arrayDish.get(position).Image);


        return convertView;
    }
}
