package com.example.lab2_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder> {

    private List < Employee > employees;
    private Context context;
    public RecyclerDataAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public RecyclerDataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee,parent,false);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapter.DataViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return employees == null ? 0 : employees.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

    }

}
