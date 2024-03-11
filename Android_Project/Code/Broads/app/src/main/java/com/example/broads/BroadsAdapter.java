package com.example.broads;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BroadsAdapter extends RecyclerView.Adapter<BroadsAdapter.ViewHolder> {
    public Context context;
    public ArrayList<Broads> arrayList ;

    public BroadsAdapter(Context context, ArrayList<Broads> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BroadsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View itemView = layoutInflater.inflate(R.layout.layout_recycler_view,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position).image);
        holder.textView.setText(arrayList.get(position).name);
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
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    RemoveItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(),"Remove item "+ textView.getText(),Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,ListTagActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("data_broad", arrayList.get(getAdapterPosition()));
                    Object REQUEST_CODE = 12345;
                    context.startActivityForResult(intent,REQUEST_CODE);
                }
            });

        }
    }
}

