package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Employee_Adapter extends RecyclerView.Adapter<Employee_Adapter.ViewHolder> {
    public Context context;
    public ArrayList<Employee> arrayList ;

    public Employee_Adapter(Context context, ArrayList<Employee> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View itemView = layoutInflater.inflate(R.layout.layout_recycler_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void RemoveItem( int position)
    {
      arrayList.remove(position);
      notifyItemRemoved(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.tv_name);
            imageView=(ImageView) itemView.findViewById((R.id.iv_name));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(),"Remove item "+ textView.getText(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
