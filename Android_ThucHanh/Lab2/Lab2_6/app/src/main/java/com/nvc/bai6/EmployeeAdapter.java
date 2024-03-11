package com.nvc.bai6;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    private Activity context;
    private List<Employee> employeeList;


    public EmployeeAdapter(Activity context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }


    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.bind(employee);


        // Đặt màu nền khác nhau dựa trên vị trí
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.white);
        } else {
            holder.itemView.setBackgroundResource(R.color.light_blue);
        }
    }


    @Override
    public int getItemCount() {
        return employeeList.size();
    }
}

