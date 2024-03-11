package com.example.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    TextView performanceEvaluation;
    TextView completionPercentage;
    EditText unscheduledJob, inProcessJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        completionPercentage = findViewById(R.id.completionPercentage);
        performanceEvaluation = findViewById(R.id.performanceEvaluation); // Thêm TextView mới
        //Công việc quá hạn
        overDueJobsEditText = findViewById(R.id.overDueJobsEditText);
        completedJobsEditText = findViewById(R.id.completedJobsEditText);
        pieChart = findViewById(R.id.pieChart);
        unscheduledJob = findViewById(R.id.unscheduledJob);
        inProcessJob = findViewById(R.id.inProcessJob);

        // Set up TextWatchers to automatically update charts when EditText values change
        overDueJobsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateCharts();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        completedJobsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateCharts();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        unscheduledJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateCharts();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        inProcessJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateCharts();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void updateCharts() {
        // Get values from EditTexts
        String overDueJobsStr = overDueJobsEditText.getText().toString();
        String completedJobsStr = completedJobsEditText.getText().toString();
        String unscheduledJobStr = unscheduledJob.getText().toString();
        String inProcessJobStr = inProcessJob.getText().toString();

        if (!overDueJobsStr.isEmpty() && !completedJobsStr.isEmpty() && !unscheduledJobStr.isEmpty() && !inProcessJobStr.isEmpty()) {
            float overDueJobs = Float.parseFloat(overDueJobsStr);
            float completedJobs = Float.parseFloat(completedJobsStr);
            float unscheduledJobs = Float.parseFloat(unscheduledJobStr);
            float inProcessJobs = Float.parseFloat(inProcessJobStr);

            float total = overDueJobs + completedJobs + unscheduledJobs + inProcessJobs;

            // Calculate percentage completion
            float percentageCompletion = (completedJobs / total) * 100;
            float percentage_unScheduled = (unscheduledJobs / total) * 100;
            float percentage_inProcess = (inProcessJobs / total) * 100;
            float percentage_overDue = (overDueJobs / total) * 100;
            // Set up PieChart
            ArrayList<PieEntry> pieEntries = new ArrayList<>();
            pieEntries.add(new PieEntry(percentageCompletion, "Completed"));
            pieEntries.add(new PieEntry(percentage_unScheduled, "Unscheduled"));
            pieEntries.add(new PieEntry(percentage_inProcess, "InProcess"));
            pieEntries.add(new PieEntry(percentage_overDue, "Overdue"));

            PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            pieDataSet.setValueTextSize(20f); // Set textSize for the values in the PieChart
            pieDataSet.setValueTextColor(Color.BLACK);

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

            completionPercentage.setText(String.format("%.2f", percentageCompletion) + "%");

            if (percentageCompletion > 70) {
                performanceEvaluation.setText("TỐT");
                performanceEvaluation.setTextColor(Color.GREEN);
            } else if (percentageCompletion > 50) {
                performanceEvaluation.setText("TRUNG BÌNH");
                performanceEvaluation.setTextColor(Color.YELLOW);
            } else {
                performanceEvaluation.setText("TỆ");
                performanceEvaluation.setTextColor(Color.RED);
            }


        }
    }
}
