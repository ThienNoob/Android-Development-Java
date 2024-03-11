package com.example.customcalendar_fortesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private LocalDate selectedDate;
    private ArrayList<LocalDate> deadlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        selectedDate = LocalDate.now();
        initDeadlines();
        displayDeadlines(1, 2023);
    }

    void initDeadlines() {
        // Tạo mảng các deadline
        deadlines = new ArrayList<>();
        deadlines.add(LocalDate.of(2024, Month.JANUARY, 10));
        deadlines.add(LocalDate.of(2024, Month.JANUARY, 15));
        deadlines.add(LocalDate.of(2024, Month.FEBRUARY, 14));
        deadlines.add(LocalDate.of(2023, Month.JANUARY, 5));
    }

    void displayDeadlines(int month, int year) {
        ArrayList<String> deadlinesInMonthYear = getDeadlinesInMonthYear(month, year);

        // Sử dụng StringBuilder để cải thiện hiệu suất khi làm việc với chuỗi
        StringBuilder textBuilder = new StringBuilder("Các ngày deadline trong tháng ")
                .append(month).append(" năm ").append(year).append("\n");

        for (String deadline : deadlinesInMonthYear) {
            textBuilder.append(deadline).append(" ");
        }

        tv.setText(textBuilder.toString());
    }

    private ArrayList<String> getDeadlinesInMonthYear(int month, int year) {
        ArrayList<String> deadlinesInThisTime = new ArrayList<>();

        // Duyệt mảng deadlines để lọc các ngày thuộc tháng và năm được chọn
        for (LocalDate deadline : deadlines) {
            if (deadline.getMonthValue() == month && deadline.getYear() == year) {
                deadlinesInThisTime.add(String.valueOf(deadline.getDayOfMonth()));
            }
        }

        return deadlinesInThisTime;
    }
}



