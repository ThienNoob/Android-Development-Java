package com.example.lab2_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    Context context;
    ArrayList<Dish> arrayDish;

    public DishAdapter(Context context, ArrayList<Dish> arrayDish) {
        this.context = context;
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
        LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.dish_layout,null);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
        tv_name.setText(arrayDish.get(position).name);
        iv_image.setImageResource(arrayDish.get(position).image);



        return convertView;
    }
}
