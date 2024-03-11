package com.example.timefomat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView selectedDateTimeTextView;
    private Button pickDateTimeButton;
    private Calendar selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedDateTimeTextView = findViewById(R.id.selectedDateTimeTextView);
        pickDateTimeButton = findViewById(R.id.pickDateTimeButton);

        pickDateTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        final int year = currentDate.get(Calendar.YEAR);
        final int month = currentDate.get(Calendar.MONTH);
        final int day = currentDate.get(Calendar.DAY_OF_MONTH);
        final int hour = currentDate.get(Calendar.HOUR_OF_DAY);
        final int minute = currentDate.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDateTime = Calendar.getInstance();
                selectedDateTime.set(Calendar.YEAR, year);
                selectedDateTime.set(Calendar.MONTH, monthOfYear);
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);

                        displaySelectedDateTime();
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void displaySelectedDateTime() {
        if (selectedDateTime != null) {
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm a", Locale.getDefault());
            String formattedCurrentDateTime = dateFormat.format(currentDate); // Ngày giờ hiện tại

            String formattedSelectedDateTime = dateFormat.format(selectedDateTime.getTime()); // Ngày giờ đã chọn

            String displayText = "Current Date & Time: " + formattedCurrentDateTime + "\n" +
                    "Selected Date & Time: " + formattedSelectedDateTime + "\n";
            String remainingTime = calculateTimeRemaining(Calendar.getInstance(), selectedDateTime);

            selectedDateTimeTextView.setText(displayText + "Time remaining: " + remainingTime);
        }
    }


    private String calculateTimeRemaining(Calendar currentDate, Calendar selectedDateTime) {
        long differenceInMillis = selectedDateTime.getTimeInMillis() - currentDate.getTimeInMillis();

        long secondsDifference = differenceInMillis / 1000;
        long minutesDifference = secondsDifference / 60;
        long hoursDifference = minutesDifference / 60;
        long daysDifference = hoursDifference / 24;

        long remainingDays = daysDifference % 30;
        long remainingHours = hoursDifference % 24;
        long remainingMinutes = minutesDifference % 60;

        return remainingDays + " days " + remainingHours + " hours " + remainingMinutes + " minutes";
    }


}
