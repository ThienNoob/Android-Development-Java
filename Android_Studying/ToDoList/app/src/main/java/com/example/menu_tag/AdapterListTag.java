package com.example.menu_tag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdapterListTag extends RecyclerView.Adapter<AdapterListTag.ViewHolder> {
    public Context context;
    public ArrayList<ListTag> arrayList;

    public AdapterListTag(Context context, ArrayList<ListTag> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterListTag.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.layout_tag,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListTag.ViewHolder holder, int position) {
        holder.tv_list_tag.setText(arrayList.get(position).name.toString());
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, arrayList.get(position).tag);
        holder.lv_list_tag.setAdapter(listViewAdapter);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static boolean kiemTraTen(String ten) {
        // Kiểm tra xem chuỗi không rỗng
        if (ten == null || ten.isEmpty()) {
            return false;
        }

        // Kiểm tra xem chuỗi chỉ chứa các ký tự chữ cái và khoảng trắng
        for (char kyTu : ten.toCharArray()) {
            if (!Character.isLetter(kyTu) && kyTu != ' ') {
                return false;
            }
        }

        return true;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_list_tag;
        ListView lv_list_tag;
        Button btn_add_tag;

        Button btn_ok;
        Button btn_cancle;


        LinearLayout ll_add_tag;
        EditText et_add_tag;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_list_tag = (TextView)itemView.findViewById(R.id.tv_list_tag);

        }
    }
}
