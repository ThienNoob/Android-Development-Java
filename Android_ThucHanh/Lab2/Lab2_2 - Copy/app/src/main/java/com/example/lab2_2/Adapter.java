package com.example.lab2_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    private Activity activity;
    Adapter ( MainActivity activity,String[] items)
    {
        this.activity=activity;
        this.items=items;
    }

    private String[] items;
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =activity.getLayoutInflater();
        View view =inflater.inflate(R.layout.item_layout,null);
        TextView textView = (TextView)view.findViewById(R.id.tv_name);
        textView.setText(items[position]);
        return  view;
    }
}
