package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{
    private TextView tv_monthYear;
    private RecyclerView rv_calendar;
    private LocalDate selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidget();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            selectedDate=LocalDate.now();
        }
        setMonthView();
    }

    private void setMonthView() {
        tv_monthYear.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        rv_calendar.setLayoutManager(layoutManager);
        rv_calendar.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate selectedDate) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth= YearMonth.from(selectedDate);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for (int i = 1; i<=42;i++)
        {
            if(i<=dayOfWeek|| i>daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");

            }
            else
            {
                daysInMonthArray.add(String.valueOf(i+dayOfWeek));
            }
        }
        return daysInMonthArray;

    }

    String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private void initWidget() {
        rv_calendar = findViewById(R.id.rv_calendar);
        tv_monthYear = findViewById(R.id.tv_monthYear);
    }

    @Override
    public void onItemClick(int position, String dayText) {

    }
}