package com.example.lab2_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class ThumbnailAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    int[] array;

    public ThumbnailAdapter(Context context, int myLayout, int[] array) {
        this.context = context;
        this.myLayout = myLayout;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.length;
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
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(myLayout,null);
        TextView textView= (TextView) convertView.findViewById(R.id.sp_thumbnail_tv_dish_name);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.sp_thumbnail_iv_dish_image);
        textView.setText("Thumbnail "+ (position+1));
        imageView.setImageResource(array[position]);
        return convertView;
    }
}
