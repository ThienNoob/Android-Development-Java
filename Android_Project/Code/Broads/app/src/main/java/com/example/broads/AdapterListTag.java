package com.example.broads;

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
            lv_list_tag = (ListView) itemView.findViewById(R.id.lv_list_tag);
            btn_add_tag = (Button) itemView.findViewById(R.id.btn_add_tag);
            ll_add_tag = (LinearLayout)itemView.findViewById(R.id.ll_add_tag);
            et_add_tag = (EditText)itemView.findViewById(R.id.et_add_tag);
            btn_ok = (Button)itemView.findViewById(R.id.btn_ok);
            btn_cancle = (Button)itemView.findViewById(R.id.btn_cancel);

            btn_add_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ll_add_tag.getVisibility()==View.GONE) {
                        ll_add_tag.setVisibility(View.VISIBLE);
                    }
                    else
                        ll_add_tag.setVisibility(View.GONE);
                    et_add_tag.setText("");
                }
            });

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kiemTraTen(et_add_tag.getText().toString()) ) {

                        arrayList.get(getAdapterPosition()).tag.add(et_add_tag.getText().toString());
                        et_add_tag.setText("");
                        notifyItemChanged(getAdapterPosition());

                    }

                    else {
                        et_add_tag.setHint("Invalid name");
                        et_add_tag.setHintTextColor(R.color.red);
                    }



                }
            });

            btn_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_add_tag.callOnClick();
                }
            });




        }
    }
}
