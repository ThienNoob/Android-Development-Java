package com.example.customcalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView  tv_MonthYear; //tv để hiện thông tin tháng năm
    private RecyclerView rv_Calendar;

    private LocalDate selectedDate; // ngày hiện tại
    public CalendarAdapter  adapterCalendar;
    ArrayList<String> deadlinesInMonth ; // những ngày có deadline trong tháng
    ArrayList<String> daysInMonth;  // những ngày trong tháng
    private ArrayList<LocalDate> deadlines; // mảng chứa thời gian có deadline



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initDeadlines();
        selectedDate = LocalDate.now();
        setMonthView();

    }


    void initDeadlines() {
        // Tạo mảng các deadline
        deadlines = new ArrayList<>();
        deadlines.add(LocalDate.of(2024, Month.JANUARY, 10));
        deadlines.add(LocalDate.of(2024, Month.JANUARY, 15));
        deadlines.add(LocalDate.of(2024, Month.FEBRUARY, 14));
        deadlines.add(LocalDate.of(2023, Month.JANUARY, 5));
        deadlines.add(LocalDate.of(2024, Month.JANUARY, 11));
        deadlines.add(LocalDate.of(2024, Month.JANUARY, 12));
        deadlines.add(LocalDate.of(2024, Month.FEBRUARY, 18));
        deadlines.add(LocalDate.of(2023, Month.JANUARY, 5));
    }

    private void initWidgets()
    {
        rv_Calendar = findViewById(R.id.rv_Calendar);
        tv_MonthYear = findViewById(R.id.tv_MonthYear);
    }


    private void setMonthView()
    {
        tv_MonthYear.setText(monthYearFromDate(selectedDate));
        daysInMonth = daysInMonthArray(selectedDate);
        deadlinesInMonth = deadlinesInMonthArray(selectedDate);
        adapterCalendar = new CalendarAdapter(daysInMonth, deadlinesInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        rv_Calendar.setLayoutManager(layoutManager);
        rv_Calendar.setAdapter(adapterCalendar);

    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private ArrayList<String> deadlinesInMonthArray(LocalDate date)
    {

        ArrayList<String> deadlinesInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int month = date.getMonthValue();
        int year = date.getYear();
        // Duyệt mảng deadlines để lọc các ngày thuộc tháng và năm được chọn
        for (LocalDate deadline : deadlines) {
            if (deadline.getMonthValue() == month && deadline.getYear() == year) {
                deadlinesInMonthArray.add(String.valueOf(deadline.getDayOfMonth()));
            }
        }

        return deadlinesInMonthArray;


    }




    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(View view, int position, String dayText)
    {
        view.setBackgroundColor(getResources().getColor(R.color.highlighted_color));

    }
}

