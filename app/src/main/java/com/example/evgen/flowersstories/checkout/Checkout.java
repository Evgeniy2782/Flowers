package com.example.evgen.flowersstories.checkout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.evgen.flowersstories.Cart.CartAdapter;
import com.example.evgen.flowersstories.R;

import java.util.Calendar;

public class Checkout extends AppCompatActivity {

    Calendar calendarTimeCurretDate = Calendar.getInstance();
    public TextView timeEditText;
    public TextView calendarTextView;

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.checkout);
        EditText editPhonePrefix = (EditText) findViewById(R.id.editePhonePrefix);
        editPhonePrefix.setEnabled(false);
        calendarTextView = findViewById(R.id.textCalendarToData);
        timeEditText = findViewById(R.id.editTextToTime);
        setInitialDateTime();

        calendarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(Checkout.this, R.style.DialogTheme,d,
                        calendarTimeCurretDate.get(Calendar.YEAR),
                        calendarTimeCurretDate.get(Calendar.MONTH),
                        calendarTimeCurretDate.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Checkout.this,R.style.DialogTheme, t,
                        calendarTimeCurretDate.get(Calendar.HOUR_OF_DAY),
                        calendarTimeCurretDate.get(Calendar.MINUTE), true)
                        .show();
            }
        });

        System.out.println("countLinerLayoutOFFandOn " + CartAdapter.countLinerLayoutOFFandOn);
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendarTimeCurretDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendarTimeCurretDate.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    private void setInitialDateTime() {

        calendarTextView.setText(DateUtils.formatDateTime(this,
                calendarTimeCurretDate.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
       //  | DateUtils.FORMAT_SHOW_TIME));

        timeEditText.setText(DateUtils.formatDateTime(this,
                calendarTimeCurretDate.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendarTimeCurretDate.set(Calendar.YEAR, year);
            calendarTimeCurretDate.set(Calendar.MONTH, monthOfYear);
            calendarTimeCurretDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}
