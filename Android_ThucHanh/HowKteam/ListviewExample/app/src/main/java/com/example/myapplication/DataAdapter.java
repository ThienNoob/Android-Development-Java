package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter {

    private String[] items;
    private Activity activity;

    public DataAdapter(MainActivity mainActivity, String[] items) {
        this.activity=mainActivity;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = activity.getLayoutInflater();

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_name.xml
        view = inflater.inflate(R.layout.item_name, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(items[i]);

        // Trả về view kết quả.
        return view;
    }
}
