package com.example.broads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Color_Adapter extends BaseAdapter {
    Context context;
    int[] color;

    public Color_Adapter(Context context, int[] color) {
        this.context = context;
        this.color = color;
    }

    @Override
    public int getCount() {
        return color.length;
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
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dialog_gridview_color_item, null);
        }
        ImageView iv_image = (ImageView) convertView.findViewById(R.id.color_item);
        iv_image.setImageResource(color[position]);

        return convertView;
    }

}
