package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    MainActivity context;
    int layout;
    ArrayList<Task> arrayList ;



    public TaskAdapter(MainActivity context, int layout, ArrayList<Task> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);
            holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            holder.iv_alter=(ImageView) convertView.findViewById(R.id.iv_alter);
            holder.iv_delete=(ImageView) convertView.findViewById(R.id.iv_delete);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.DeleteTask(arrayList.get(position).id);
                    context.getTask();
                }
            });

            holder.iv_alter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Task task = arrayList.get(position);
        holder.tv_name.setText(task.name.toString());
        return convertView ;
    }

    class ViewHolder
    {
        TextView tv_name;
        ImageView iv_delete,iv_alter;

    }
}
