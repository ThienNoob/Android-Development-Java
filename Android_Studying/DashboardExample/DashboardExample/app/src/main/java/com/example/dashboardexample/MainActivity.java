package com.example.dashboardexample;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText overDueJobsEditText;
    private EditText completedJobsEditText;
    private PieChart pieChart;
    private HorizontalBarChart horizontalBarChart;

    EditText unscheduledJobEditText, inProcessJobEditText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();

        // Set up TextWatchers to automatically update charts when EditText values change
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogDashboard();
            }
        });

    }
    void findViewByIds()
    {
        //Công việc quá hạn
        overDueJobsEditText = findViewById(R.id.overDueJobsEditText);
        completedJobsEditText = findViewById(R.id.completedJobsEditText);
        unscheduledJobEditText = findViewById(R.id.unscheduledJobEditText);
        inProcessJobEditText = findViewById(R.id.inProcessJobEditText);
        button = (Button) findViewById(R.id.button);
    }

    private void updateCharts() {


        // Get values from EditTexts
        String overDueJobsStr = overDueJobsEditText.getText().toString();
        String completedJobsStr = completedJobsEditText.getText().toString();
        String unscheduledJobStr = unscheduledJobEditText.getText().toString();
        String inProcessJobStr = inProcessJobEditText.getText().toString();

        if (!overDueJobsStr.isEmpty() && !completedJobsStr.isEmpty() && !unscheduledJobStr.isEmpty() && !inProcessJobStr.isEmpty()) {
            float overDueJobs = Float.parseFloat(overDueJobsStr);
            float completedJobs = Float.parseFloat(completedJobsStr);
            float unscheduledJobs = Float.parseFloat(unscheduledJobStr);
            float inProcessJobs = Float.parseFloat(inProcessJobStr);

            float total = overDueJobs + completedJobs + unscheduledJobs + inProcessJobs;

            // Calculate percentage completion
            float percentage_completion = (completedJobs / total) * 100;
            float percentage_unScheduled = (unscheduledJobs / total) * 100;
            float percentage_inProcess = (inProcessJobs / total) * 100;
            float percentage_overDue = (overDueJobs / total) * 100;
            // Set up PieChart
            ArrayList<PieEntry> pieEntries = new ArrayList<>();
            pieEntries.add(new PieEntry(percentage_completion, "Đã hoàn thành"));
            pieEntries.add(new PieEntry(percentage_unScheduled, "Chưa đặt lịch"));
            pieEntries.add(new PieEntry(percentage_inProcess, "Đang thực hiện"));
            pieEntries.add(new PieEntry(percentage_overDue, "Trễ hạn"));

            PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
            //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

            int unscheduledColor = ContextCompat.getColor(this, R.color.unscheduled);
            int inProcessColor = ContextCompat.getColor(this, R.color.in_process);
            int completedColor = ContextCompat.getColor(this, R.color.completed);
            int overdueColor = ContextCompat.getColor(this, R.color.overdue);

            int[] customColors = new int[]{completedColor, unscheduledColor, inProcessColor, overdueColor};
            pieDataSet.setColors(customColors);
            pieDataSet.setColors(customColors);
            pieDataSet.setColors(customColors);
            pieDataSet.setValueTextSize(20f); // Set textSize for the values in the PieChart
            pieDataSet.setValueTextColor(Color.WHITE);

            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.getDescription().setEnabled(false);
            pieChart.invalidate();

            // Hiển thị tổng công việc giữa PieChart
            pieChart.setCenterText("Total Jobs\n" + String.format("%.0f", total));
            pieChart.setCenterTextSize(20f);
            pieChart.setCenterTextColor(Color.BLACK);

            // Thay đổi text color
            Legend legend = pieChart.getLegend();
            legend.setTextColor(Color.BLACK);

        }
    }
    void openDiaLogDashboard()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_dashboard);

        Window window = dialog.getWindow();
        if (window == null)
            return;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
       // window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        pieChart = dialog.findViewById(R.id.pieChart);
        updateCharts();



        dialog.setCancelable(true);

        dialog.show();
    }

}
