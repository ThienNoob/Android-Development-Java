package com.example.customcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    public CalendarAdapter  adapter;
    ArrayList<String> deadlinesInMonth ;
    ArrayList<String> daysInMonth;
    private ArrayList<LocalDate> deadlines;


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
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        daysInMonth = daysInMonthArray(selectedDate);
        deadlinesInMonth = deadlinesInMonthArray(selectedDate);
        adapter = new CalendarAdapter(daysInMonth, deadlinesInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(adapter);

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

