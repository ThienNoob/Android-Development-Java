package com.example.lab2_1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private String[] items;
    public Adapter(MainActivity mainActivity, String[] items) {
        this.activity=mainActivity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.length ;
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
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = activity.getLayoutInflater();

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_name.xml
        View view = inflater.inflate(R.layout.item_layout, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(items[position]);

        // Trả về view kết quả.
        return view;
    }
}
